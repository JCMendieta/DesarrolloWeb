package co.edu.javeriana.proyectoWeb.api_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import co.edu.javeriana.proyectoWeb.model.Monster;
import co.edu.javeriana.proyectoWeb.repository.ExitRepository;
import co.edu.javeriana.proyectoWeb.repository.ItemRepository;
import co.edu.javeriana.proyectoWeb.repository.PlayerRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterRepository;

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

    @Autowired
    MonsterRepository monsterRepository;

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
        Room room = player.getIdRoom();

        player.getItems().remove(item);
        player.setWeight(player.getWeight() - item.getWeight());
        item.getIdRoom().add(room);
        item.setIdPlayer(null);
        room.getrItems().add(item);

        playerRepository.save(player);
        itemRepository.save(item);
        roomRepository.save(room);
        
        return player;
    }

    @GetMapping("/spawn/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player spawn (@PathVariable Long id)
    {
        Player player = playerRepository.findById(id).get();
        List<Room> rooms = roomRepository.findAll();
        player.setIdRoom(roomRepository.findById((long)16).get());
        player.setClock((long)0);
        player.setHitpoints((long)40);
        player.setItems(new ArrayList<Item>());
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

        if (room.getrMonster() == null && player.getWeight() + item.getWeight() <= player.getMaxWeight())
        {
            player.getItems().add(item);
            player.setWeight(player.getWeight() + item.getWeight());
            item.setIdPlayer(player);
            item.getIdRoom().remove(room);
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
        Room nextRoom = exit.getIdSRoom();

        player.setIdRoom(nextRoom);
        Long newClock = player.getClock() + 1;
        player.setClock(newClock);
        nextRoom.getrPlayers().add(player);
        
        playerRepository.save(player);
        exitRepository.save(exit);
        roomRepository.save(nextRoom);

        return player;
    }

    @GetMapping("/currentPlayers/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Player> players (@PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        
        return room.getrPlayers();
    }

    @GetMapping("/attack/{idPlayer}/{idMonster}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Player attack (@PathVariable Long idPlayer, @PathVariable Long idMonster)
    {
        Player player = playerRepository.findById(idPlayer).get();
        Monster monster = monsterRepository.findById(idMonster).get();
        Room room = monster.getIdRoom();
        Random random = new Random();
        Long damagePlayer = (long)random.nextLong((player.getAttack_level()+1));
        Long damageMonster = (long)random.nextLong((monster.getIdMonsterType().getAttack_level()+1));
        Long defenseMonster = (long)random.nextLong((monster.getIdMonsterType().getDefence_slash()+1));
        Long defensePlayer = (long)random.nextLong((player.getDefence_slash()+1));
        Long totalDamageToMonster = defenseMonster - damagePlayer;
        Long totalDamageToPlayer = defensePlayer - damageMonster;

        if(totalDamageToMonster > 0){
            totalDamageToMonster = (long)0;
        }
        else if(totalDamageToMonster < 0){
            totalDamageToMonster = totalDamageToMonster * -1;
        }
        if(totalDamageToPlayer > 0){
            totalDamageToPlayer = (long)0;
        }
        else if(totalDamageToPlayer < 0){
            totalDamageToPlayer = totalDamageToPlayer *-1;
        }

        Long newHpMonster = monster.getHitpoints()-(long)totalDamageToMonster;
        Long newHpPlayer = player.getHitpoints()-(long)totalDamageToPlayer;

        if(newHpMonster <= 0){
            monster.setHitpoints((long)0);
            room.setrMonster(null); 
        }
        else{
            monster.setHitpoints(newHpMonster);
            if(newHpPlayer <=0){
                player.setHitpoints((long)0);
                //COMPLETAR CON QUE OCURRE CUANDO EL JUGADOR MUERE
            }
            else{
                player.setHitpoints(newHpPlayer);
            }
        }

        roomRepository.save(room);
        playerRepository.save(player);
        monsterRepository.save(monster);
        
        return player;
    }

    @GetMapping("/finish/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Long finish (@PathVariable Long id)
    {
        Player player = playerRepository.findById(id).get();
        Long score = (long)0;

        for (Item i : player.getItems())
        {
            score = score + i.getCost();
        }

        return score;
    }
}