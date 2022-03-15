package co.edu.javeriana.proyectoWeb.model;

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
        Optional<Player> p = playerRepository.findById(id);
        
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

}