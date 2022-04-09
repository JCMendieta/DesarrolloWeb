package co.edu.javeriana.proyectoWeb.api_controller;

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

import co.edu.javeriana.proyectoWeb.model.Exit;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.ExitRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

@RestController
@RequestMapping("/exit_api")
public class ExitApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ExitRepository exitRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Exit> list(Model model)
    {
        List<Exit> exits = exitRepository.findAll();
        model.addAttribute("exits", exits);
        return exits;
    }

    @GetMapping("/edit/{id}")
    public Exit edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Exit p = exitRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("exit", p);
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
            exitRepository.findById(id).get().unlinkRoomExit(room);
        }

        exitRepository.deleteById(id);
    }


    @PostMapping("/save")
    public Exit save(@ModelAttribute Exit exit, Model model) 
    {
        return exitRepository.save(exit);
    }

    @PostMapping("/create")
    public Exit create(Model model) 
    {
        Exit exit = new Exit(new Room(), new Room());
        model.addAttribute("exit", exit);
        return exit;
    }

    @GetMapping("/view/{id}")
    public Exit view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Exit p = exitRepository.findById(id).get();

        if (p != null) 
        {
            Exit  exit = exitRepository.findById(id).get();
            model.addAttribute("exit", exit);
            return exit;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }
}