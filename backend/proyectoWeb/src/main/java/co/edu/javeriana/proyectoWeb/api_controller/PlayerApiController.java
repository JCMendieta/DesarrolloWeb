package co.edu.javeriana.proyectoWeb.api_controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.proyectoWeb.model.Exit;
import co.edu.javeriana.proyectoWeb.model.Item;
import co.edu.javeriana.proyectoWeb.model.Player;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.ExitRepository;
import co.edu.javeriana.proyectoWeb.repository.ItemRepository;
import co.edu.javeriana.proyectoWeb.repository.PlayerRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

@RestController
@RequestMapping("/player_api")
public class PlayerApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ExitRepository exitRepository;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Player> list(Model model)
    {
        List<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return players;
    }

    @GetMapping("/edit/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Player p = playerRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("player", p);
            return p;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player save(@ModelAttribute Player player, Model model) 
    {
        return playerRepository.save(player);
    }

    @GetMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void delete(Model model, @PathVariable Long id)
    {
        Player player = playerRepository.findById(id).get();
        Room r = player.getIdRoom();

        if (r != null)
        {
            player.unlinkRoomPlayer(r);
            roomRepository.save(r);
        }

        for (Item item : player.getItems())
        {
            item.setIdPlayer(null);
            itemRepository.save(item);
        }
        
        playerRepository.deleteById(id);
    }

    @GetMapping("/view/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Player p = playerRepository.findById(id).get();

        if (p != null) 
        {
            Player player = playerRepository.findById(id).get();
            model.addAttribute("player", player);
            return player;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/item_list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Item> currentItems(Model model, @PathVariable Long id)
    {
        Player p = playerRepository.findById(id).get();
        List<Item> items = p.getItems();
        model.addAttribute("items", items);
        return items;
    }

    @GetMapping("/{username}/{password}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player findPlayerByUsername(@PathVariable String username, @PathVariable String password) 
    {
        Player player = null;

        for (Player p : playerRepository.findAll())
        {
            if (p.getName().equals(username) && p.getPassword().equals(password))
            {
                player = p;
            }
        }

        return player;
    }

    @GetMapping("/discard/{idPlayer}/{idItem}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player discard (@PathVariable Long idPlayer, @PathVariable Long idItem)
    {
        Player player = playerRepository.findById(idPlayer).get();
        Item item = itemRepository.findById(idItem).get();
        player.getItems().remove(item);
        item.setIdPlayer(null);
        player.getIdRoom().getrItems().add(item);
        playerRepository.save(player);
        itemRepository.save(item);
        roomRepository.save(player.getIdRoom());
        
        return player;
    }

    @GetMapping("/spawn/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player spawn (@PathVariable Long id)
    {
        Player player = playerRepository.findById(id).get();
        List<Room> rooms = roomRepository.findAll();
        //Long r = (long)(Math.random() * (rooms.get(rooms.size() - 1).getId() - rooms.get(0).getId()));
        player.setIdRoom(roomRepository.findById((long)16).get());
        playerRepository.save(player);

        return player;
    }

    @GetMapping("/collect/{idPlayer}/{idItem}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player collect (@PathVariable Long idPlayer, @PathVariable Long idItem)
    {
        Player player = playerRepository.findById(idPlayer).get();
        Item item = itemRepository.findById(idItem).get();
        Room room = player.getIdRoom();
        Long actualWeight = (long)0;

        for (Item i : player.getItems())
        {
            actualWeight = actualWeight + i.getWeight();
        }

        if (room.getrMonster() == null && actualWeight + item.getWeight() <= player.getMaxWeight())
        {
            player.getItems().add(item);
            item.setIdPlayer(player);
            room.getrItems().remove(item);
            playerRepository.save(player);
            itemRepository.save(item);
            roomRepository.save(room);
        }

        return player;
    }

    @GetMapping("/move/{idPlayer}/{idExit}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player move (@PathVariable Long idPlayer, @PathVariable Long idExit)
    {
        Player player = playerRepository.findById(idPlayer).get();
        Exit exit = exitRepository.findById(idExit).get();
        
        player.setIdRoom(exit.getIdSRoom());

        playerRepository.save(player);
        exitRepository.save(exit);

        return player;
    }
}