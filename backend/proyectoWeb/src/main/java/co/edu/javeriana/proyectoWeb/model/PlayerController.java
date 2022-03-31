package co.edu.javeriana.proyectoWeb.model;

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
@RequestMapping("/player")
public class PlayerController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/list")
    public String list(Model model)
    {
        Iterable<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "player-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Player p = playerRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("player", p);
            return "player-edit";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Player player, Model model) 
    {
        playerRepository.save(player);
        return "redirect:/player/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id)
    {
        Player player = playerRepository.findById(id).get();
        Room r=player.idRoom;
        if(r != null){
            player.unlinkRoomPlayer(r);
            roomRepository.save(r);
        }
        for(Item item : player.getItems() ){
            item.setIdPlayer(null);
            itemRepository.save(item);
        }
        
        playerRepository.deleteById(id);
        return "redirect:/player/list";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Player p = playerRepository.findById(id).get();

        if (p != null) 
        {
            Player player = playerRepository.findById(id).get();
            model.addAttribute("player", player);
            return "player-view";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/item_list/{id}")
    public String currentItems(Model model, @PathVariable Long id)
    {
        Player p = playerRepository.findById(id).get();
        Iterable<Item> items = p.items;
        model.addAttribute("items", items);
        return "player-items";
    }

}