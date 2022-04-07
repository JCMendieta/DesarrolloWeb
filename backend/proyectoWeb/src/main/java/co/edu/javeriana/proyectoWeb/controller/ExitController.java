package co.edu.javeriana.proyectoWeb.controller;

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

import co.edu.javeriana.proyectoWeb.model.Exit;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.ExitRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

@Controller
@RequestMapping("/exit")
public class ExitController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ExitRepository exitRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    public String list(Model model)
    {
        Iterable<Exit> exits = exitRepository.findAll();
        model.addAttribute("exits", exits);
        return "exit-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Exit p = exitRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("exit", p);
            return "exit-edit";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id)
    {
        for (Room room : roomRepository.findAll())
        {
            exitRepository.findById(id).get().unlinkRoomExit(room);
        }

        exitRepository.deleteById(id);

        return "redirect:/exit/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Exit exit, Model model) 
    {
        exitRepository.save(exit);
        return "redirect:/exit/list";
    }

    @PostMapping("/create")
    public String create(Model model) 
    {
        Exit exit = new Exit(new Room(), new Room());
        model.addAttribute("exit", exit);
        return "exit-create";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Exit p = exitRepository.findById(id).get();

        if (p != null) 
        {
            Exit  exit = exitRepository.findById(id).get();
            model.addAttribute("exit", exit);
            return "exit-view";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }
}