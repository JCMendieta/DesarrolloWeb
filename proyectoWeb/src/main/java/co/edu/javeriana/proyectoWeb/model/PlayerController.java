package co.edu.javeriana.proyectoWeb.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/list")
    public String itemList(Model model)
    {
        Iterable<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "player-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id)
    {
        Player player = playerRepository.findById(id).get();
        Room r=player.idRoom;
        if(r != null){
            player.unlinkRoomPlayer(r);
        }
        playerRepository.deleteById(id);
        return "redirect:/player/list";
    }

}