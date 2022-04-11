package co.edu.javeriana.proyectoWeb.controller;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

@Controller
@RequestMapping("/room")
public class RoomController 
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
    public String list(Model model)
    {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "room-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        Room p = roomRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("room", p);
            return "room-edit";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Room room, Model model) 
    {
        roomRepository.save(room);
        return "redirect:/room/list";
    }

    @PostMapping("/create")
    public String create(Model model) 
    {
        Room room = new Room(new ArrayList<Item>(), new ArrayList<DecorativeItem>(), new Monster(), new ArrayList<Exit>(), new ArrayList<Player>());
        model.addAttribute("room", room);
        return "room-create";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id)
    {
        for (Room room : roomRepository.findAll())
        {
            for (Exit exit : room.getrExits())
            {
                if(exit.getIdFRoom() != null && exit.getIdFRoom().getId() == id || exit.getIdSRoom() != null && exit.getIdSRoom().getId() == id)
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

        return "redirect:/room/list";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Room p = roomRepository.findById(id).get();

        if (p != null) 
        {
            Room room =  roomRepository.findById(id).get();
            model.addAttribute("room", room);
            return "room-view";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/view_monster/{id}")
    public String viewMonster(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Room room = roomRepository.findById(id).get();
        Monster monster = room.getrMonster();

        model.addAttribute("monster", monster);

        return "room-view-monster";
    }

    @GetMapping("/view_item_list/{id}")
    public String viewCurrentItems(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        Iterable<Item> items = room.getrItems();
        model.addAttribute("items", items);
        return "room-view-itemList";
    }
    @GetMapping("/view_decorativeItem_list/{id}")
    public String viewCurrentDecorativeItems(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        Iterable<DecorativeItem> decorativeItems = room.getIdDecorativeItem();
        model.addAttribute("decorativeItems", decorativeItems);
        return "room-view-decorativeItemList";
    }

    @GetMapping("view_exit_list/{id}")
    public String viewCurrentExits(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        Iterable<Exit> exits = room.getrExits();
        model.addAttribute("exits", exits);
        return "room-view-exits";
    }
    
    @GetMapping("view_player_list/{id}")
    public String viewCurrentPlayers(Model model, @PathVariable Long id)
    {
        Room room = roomRepository.findById(id).get();
        Iterable<Player> players = room.getrPlayers();
        model.addAttribute("players", players);
        return "room-view-players";
    }
    
    @GetMapping("/item_list/{id}")
    public String itemList (Model model, @PathVariable Long id)
    {
        Iterable<Item> items = itemRepository.findAll();
        Room r = roomRepository.findById(id).get();
        model.addAttribute("items", items);
        model.addAttribute("room", r);
        return "room-items";
    }

    @GetMapping("/item_list_c/{id}")
    public String currentItems(Model model, @PathVariable Long id)
    {
        
        Room r = roomRepository.findById(id).get();
        Iterable<Item> items = r.getrItems();
        model.addAttribute("items", items);
        return "room-current-items";
    }

    @GetMapping("/add_item/{idRoom}/{idItem}")
    public String addItem (@PathVariable Long idRoom, @PathVariable Long idItem)
    {
        Room r = roomRepository.findById(idRoom).get();
        Item i = itemRepository.findById(idItem).get();
        r.getrItems().add(i);
        itemRepository.save(i);
        return "redirect:/room/list";
    }

    @GetMapping("/ditem_list/{id}")
    public String decorativeItemList (Model model, @PathVariable Long id)
    {
        Iterable<DecorativeItem> decorativeItems = decorativeItemRepository.findAll();
        Room r = roomRepository.findById(id).get();
        model.addAttribute("decorativeItems", decorativeItems);
        model.addAttribute("room", r);
        return "room-ditems";
    }

    @GetMapping("/ditem_list_c/{id}")
    public String currentDecorativeItems(Model model, @PathVariable Long id)
    {
        Room r = roomRepository.findById(id).get();
        Iterable<DecorativeItem> decorativeItems = r.getIdDecorativeItem();
        model.addAttribute("decorativeItems", decorativeItems);
        return "room-current-ditems";
    }

    @GetMapping("/add_ditem/{idRoom}/{idItem}")
    public String addDecorativeItem (@PathVariable Long idRoom, @PathVariable Long idItem)
    {
        Room r = roomRepository.findById(idRoom).get();
        DecorativeItem di = decorativeItemRepository.findById(idItem).get();
        r.getIdDecorativeItem().add(di);
        decorativeItemRepository.save(di);
        return "redirect:/room/list";
    }
}