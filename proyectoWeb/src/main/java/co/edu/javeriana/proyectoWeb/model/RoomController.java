package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/list")
    public String monsterList(Model model)
    {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "room-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Optional<Room> p = roomRepository.findById(id);
        
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

    @GetMapping("/create")
    public String createRoom(Model model) 
    {
        Room room = new Room(new ArrayList<Item>(), new ArrayList<DecorativeItem>(), new Monster(), new ArrayList<Exit>(), new ArrayList<Player>());
        model.addAttribute("room", room);
        return "room-create";
    }

    @PostMapping("/create_exit")
    public String createExit(Model model)
    {
        Exit exit = new Exit(new Room(), new Room());
        model.addAttribute("exit", exit);
        return "exit-create";
    }

    @PostMapping("/create_item")
    public String createItem(Model model) 
    {
        Item item = new Item("", "", (long)0, (long)0, "", "", new ArrayList<Room>(), new Player());
        model.addAttribute("item", item);
        return "item-create";
    }

    @PostMapping("/create_ditem")
    public String createDItem(Model model) 
    {
        DecorativeItem decorativeItem = new DecorativeItem("", new ArrayList<Room>());
        model.addAttribute("decorativeItem", decorativeItem);
        return "decorativeItem-create";
    }

    @PostMapping("/create_monster")
    public String createMonster(Model model) 
    {
        Monster monster = new Monster ((long)0, new Room (), new MonsterType ());
        model.addAttribute("monster", monster);
        return "monster-create";
    }

    
    @PostMapping("/create_monstertype")
    public String createMonsterType(Model model) 
    {
        MonsterType monsterType = new MonsterType("", "", (long)0, (long)0, (long)0, (long)0, new ArrayList<String>(), new ArrayList<Monster>(), "", "");
        model.addAttribute("monsterType", monsterType);
        return "monsterType-create";
    }
}