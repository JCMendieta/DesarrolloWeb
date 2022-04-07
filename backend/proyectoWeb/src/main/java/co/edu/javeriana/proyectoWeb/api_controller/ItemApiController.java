package co.edu.javeriana.proyectoWeb.api_controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.ui.Model;
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
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

@RestController
@RequestMapping("/item_api")
public class ItemApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    public List<Item> list(Model model)
    {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return items;
    }

    @GetMapping("/edit/{id}")
    public Item edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Item p = itemRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("item", p);
            return p;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/delete/{id}")
    public void delete(Model model, @PathVariable Long id)
    {
        for (Room room : roomRepository.findAll())
        {
            itemRepository.findById(id).get().unlinkRoomItem(room);
        }

        itemRepository.deleteById(id);
    }

    @PostMapping("/save")
    public Item save(@ModelAttribute Item item, Model model) 
    {
        return itemRepository.save(item);
    }

    @PostMapping("/create")
    public Item create(Model model) 
    {
        Item item = new Item("", "", (long)0, (long)0, "", "", new ArrayList<Room>(), new Player());
        model.addAttribute("item", item);
        return item;
    }

    @GetMapping("/view/{id}")
    public Item view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Item p = itemRepository.findById(id).get();

        if (p != null) 
        {
            Item  item = itemRepository.findById(id).get();
            model.addAttribute("item", item);
            return item;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("view_room_list/{id}")
    public List<Room> viewCurrentRoomsWithItem(Model model, @PathVariable Long id)
    {
        Item item = itemRepository.findById(id).get();
        List<Room> rooms = item.getIdRoom();
        model.addAttribute("rooms", rooms);
        return rooms;
    }

    @GetMapping("view_player_list/{id}")
    public Player viewCurrentPlayerWithItem(Model model, @PathVariable Long id)
    {
        Item item = itemRepository.findById(id).get();
        Player player = item.getIdPlayer();

        if (player == null)
        {
            player = new Player();
            player.setName("None");
        }

        model.addAttribute("player", player);
        return player;
    }
}