package co.edu.javeriana.proyectoWeb.api_controller;

import java.util.ArrayList;
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

import co.edu.javeriana.proyectoWeb.model.DecorativeItem;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.DecorativeItemRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

@RestController
@RequestMapping("/decorative_item_api")
public class DecorativeItemApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    DecorativeItemRepository decorativeItemRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<DecorativeItem> list(Model model)
    {
        List<DecorativeItem>  decorativeItems = decorativeItemRepository.findAll();
        model.addAttribute("decorativeItems", decorativeItems);
        return decorativeItems;
    }

    @GetMapping("/edit/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public DecorativeItem edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        DecorativeItem p = decorativeItemRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("decorativeItem", p);
            return p;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void delete(Model model, @PathVariable Long id)
    {
        for (Room room : roomRepository.findAll())
        {
            decorativeItemRepository.findById(id).get().unlinkRoomDecorativeItem(room);
        }

        decorativeItemRepository.deleteById(id);
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:4200")
    public DecorativeItem save(@ModelAttribute DecorativeItem decorativeItem, Model model) 
    {
        return decorativeItemRepository.save(decorativeItem);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public DecorativeItem create(Model model) 
    {
        DecorativeItem decorativeItem = new DecorativeItem("", new ArrayList<Room>());
        model.addAttribute("decorativeItem", decorativeItem);
        return decorativeItem;
    }

    @GetMapping("/view/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public DecorativeItem view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        DecorativeItem p = decorativeItemRepository.findById(id).get();

        if (p != null) 
        {
            DecorativeItem  decorativeItem = decorativeItemRepository.findById(id).get();
            model.addAttribute("decorativeItem", decorativeItem);
            return decorativeItem;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }
}