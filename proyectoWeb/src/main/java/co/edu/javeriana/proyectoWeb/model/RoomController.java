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
    public String list(Model model)
    {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "room-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
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

    @PostMapping("/create")
    public String create(Model model) 
    {
        Room room = new Room(new ArrayList<Item>(), new ArrayList<DecorativeItem>(), new Monster(), new ArrayList<Exit>(), new ArrayList<Player>());
        model.addAttribute("room", room);
        return "room-create";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Optional<Room> p = roomRepository.findById(id);

        if (p != null) 
        {
            model.addAttribute("room", p);
            return "room-view";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }
}