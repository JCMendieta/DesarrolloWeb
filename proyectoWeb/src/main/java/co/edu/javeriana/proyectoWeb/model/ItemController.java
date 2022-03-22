package co.edu.javeriana.proyectoWeb.model;

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

@Controller
@RequestMapping("/item")
public class ItemController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    public String list(Model model)
    {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "item-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Item p = itemRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("item", p);
            return "item-edit";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id)
    {
        for( Room room : roomRepository.findAll() ){
            itemRepository.findById(id).get().unlinkRoomItem(room);
        }
        itemRepository.deleteById(id);
        return "redirect:/item/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Item item, Model model) 
    {
        itemRepository.save(item);
        return "redirect:/item/list";
    }

    @PostMapping("/create")
    public String create(Model model) 
    {
        Item item = new Item("", "", (long)0, (long)0, "", "", new ArrayList<Room>(), new Player());
        model.addAttribute("item", item);
        return "item-create";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Item p = itemRepository.findById(id).get();

        if (p != null) 
        {
            Item  item = itemRepository.findById(id).get();
            model.addAttribute("item", item);
            return "item-view";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("view_room_list/{id}")
    public String viewCurrentRoomsWithItem(Model model, @PathVariable Long id)
    {
        Item item = itemRepository.findById(id).get();
        Iterable<Room> rooms = item.idRoom;
        model.addAttribute("rooms", rooms);
        return "item-view-rooms";
    }
    @GetMapping("view_player_list/{id}")
    public String viewCurrentPlayerWithItem(Model model, @PathVariable Long id)
    {
        Item item = itemRepository.findById(id).get();
        Player player= item.idPlayer;
        if(player == null){
            player = new Player();
            player.name ="None";
        }
        model.addAttribute("player", player);
        return "item-view-player";
    }
  
}