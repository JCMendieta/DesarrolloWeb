package co.edu.javeriana.proyectoWeb.api_controller;

import java.util.ArrayList;
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

import co.edu.javeriana.proyectoWeb.model.DecorativeItem;
import co.edu.javeriana.proyectoWeb.model.Exit;
import co.edu.javeriana.proyectoWeb.model.Item;
import co.edu.javeriana.proyectoWeb.model.Monster;
import co.edu.javeriana.proyectoWeb.model.Player;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.DecorativeItemRepository;
import co.edu.javeriana.proyectoWeb.repository.ExitRepository;
import co.edu.javeriana.proyectoWeb.repository.ItemRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterRepository;
import co.edu.javeriana.proyectoWeb.repository.PlayerRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

@RestController
@RequestMapping("/room_api")
public class RoomApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ExitRepository exitRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    DecorativeItemRepository decorativeItemRepository;
    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Room> list(Model model)
    {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return rooms;
    }

    @GetMapping("/edit/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Room edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        Room p = roomRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("room", p);
            return p;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:4200")
    public Room save(@ModelAttribute Room room, Model model) 
    {
        return roomRepository.save(room);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public Room create(Model model) 
    {
        Room room = new Room(new ArrayList<Item>(), new ArrayList<DecorativeItem>(), new Monster(), new ArrayList<Exit>(), new ArrayList<Player>());
        model.addAttribute("room", room);
        return room;
    }

    @GetMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void delete(Model model, @PathVariable Long id)
    {
        for (Room room : roomRepository.findAll())
        {
            for (Exit exit : room.getrExits())
            {
                if (exit.getIdFRoom() != null && exit.getIdFRoom().getId() == id || exit.getIdSRoom() != null && exit.getIdSRoom().getId() == id)
                {
                    exit.setIdFRoom(null);
                    exit.setIdSRoom(null);
                    exitRepository.save(exit);
                }
            }  
        }

        Room room = roomRepository.findById(id).get();
        
        for (Exit exit : room.getrExits())
        {
            exit.unlinkRoomExit(room);
            exitRepository.delete(exit); 
        }

        for (Player player : room.getrPlayers())
        {
            player.setIdRoom(null);
            playerRepository.save(player);
        }

        if (room.getrMonster() != null)
        {
            room.getrMonster().setIdRoom(null);
        }

        roomRepository.findById(id).get().unlinkRoomAttributes();
        roomRepository.save(room);
        roomRepository.deleteById(id);
    }

    @GetMapping("/view/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Room view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Room p = roomRepository.findById(id).get();

        if (p != null) 
        {
            Room room =  roomRepository.findById(id).get();
            model.addAttribute("room", room);
            return room;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/view_monster/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Monster viewMonster(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Room room = roomRepository.findById(id).get();
        Monster monster = room.getrMonster();

        model.addAttribute("monster", monster);

        return monster;
    }

    @GetMapping("/view_item_list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Item> viewCurrentItems(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        List<Item> items = room.getrItems();
        model.addAttribute("items", items);
        
        return items;
    }

    @GetMapping("/view_decorativeItem_list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<DecorativeItem> viewCurrentDecorativeItems(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        List<DecorativeItem> decorativeItems = room.getIdDecorativeItem();
        model.addAttribute("decorativeItems", decorativeItems);

        return decorativeItems;
    }

    @GetMapping("view_exit_list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Exit> viewCurrentExits(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        List<Exit> exits = room.getrExits();
        model.addAttribute("exits", exits);

        return exits;
    }

    @GetMapping("view_player_list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Player> viewCurrentPlayers(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        List<Player> players = room.getrPlayers();
        model.addAttribute("players", players);

        return players;
    }
    
    @GetMapping("/item_list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Item> itemList (Model model, @PathVariable Long id)
    {
        List<Item> items = itemRepository.findAll();
        Room r = roomRepository.findById(id).get();
        model.addAttribute("items", items);
        model.addAttribute("room", r);

        return items;
    }

    @GetMapping("/item_list_c/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Item> currentItems(Model model, @PathVariable Long id)
    {
        
        Room r = roomRepository.findById(id).get();
        List<Item> items = r.getrItems();
        model.addAttribute("items", items);

        return items;
    }

    @GetMapping("/add_item/{idRoom}/{idItem}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Item addItem (@PathVariable Long idRoom, @PathVariable Long idItem)
    {
        Room r = roomRepository.findById(idRoom).get();
        Item i = itemRepository.findById(idItem).get();
        r.getrItems().add(i);
        itemRepository.save(i);

        return i;
    }

    @GetMapping("/ditem_list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<DecorativeItem> decorativeItemList (Model model, @PathVariable Long id)
    {
        List<DecorativeItem> decorativeItems = decorativeItemRepository.findAll();
        Room r = roomRepository.findById(id).get();
        model.addAttribute("decorativeItems", decorativeItems);
        model.addAttribute("room", r);

        return decorativeItems;
    }

    @GetMapping("/ditem_list_c/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<DecorativeItem> currentDecorativeItems(Model model, @PathVariable Long id)
    {
        Room r = roomRepository.findById(id).get();
        List<DecorativeItem> decorativeItems = r.getIdDecorativeItem();
        model.addAttribute("decorativeItems", decorativeItems);

        return decorativeItems;
    }

    @GetMapping("/add_ditem/{idRoom}/{idItem}")
    @CrossOrigin(origins = "http://localhost:4200")
    public DecorativeItem addDecorativeItem (@PathVariable Long idRoom, @PathVariable Long idItem)
    {
        Room r = roomRepository.findById(idRoom).get();
        DecorativeItem di = decorativeItemRepository.findById(idItem).get();
        r.getIdDecorativeItem().add(di);
        decorativeItemRepository.save(di);

        return di;
    }
}