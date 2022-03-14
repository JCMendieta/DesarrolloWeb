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
@RequestMapping("/room")
public class RoomController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ExitRepository exitRepository;

    @GetMapping("/list")
    public String monsterList(Model model)
    {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "room-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id)
    {
        for(Room room : roomRepository.findAll()){
            if(room != null){
                for (Exit exit : room.getrExits()){
                    if (exit != null){
                        if(exit.idFRoom != null && exit.idFRoom.getId() == id){
                            exit.setIdFRoom(null);
                            exit.setIdSRoom(null);
                        }
                        if(exit.idSRoom != null && exit.idSRoom.getId() == id){
                            exit.setIdSRoom(null);
                            exit.setIdFRoom(null);
                        }
                    }
                }
            }
        }
        for(Exit exit : roomRepository.findById(id).get().getrExits()){
            exitRepository.delete(exit);
        }
        roomRepository.findById(id).get().unlinkRoomAttributes();
        roomRepository.save(roomRepository.findById(id).get());
        roomRepository.deleteById(id);
        return "redirect:/room/list";
    }

}