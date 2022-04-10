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

import co.edu.javeriana.proyectoWeb.model.Item;
import co.edu.javeriana.proyectoWeb.model.Player;
import co.edu.javeriana.proyectoWeb.model.Room;
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
}