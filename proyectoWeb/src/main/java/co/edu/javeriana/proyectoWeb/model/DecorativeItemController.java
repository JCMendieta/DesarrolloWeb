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
@RequestMapping("/decorative_item")
public class DecorativeItemController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    DecorativeItemRepository decorativeItemRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    public String list(Model model)
    {
        Iterable<DecorativeItem>  decorativeItems = decorativeItemRepository.findAll();
        model.addAttribute("decorativeItems", decorativeItems);
        return "decorativeItem-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Optional<DecorativeItem> p = decorativeItemRepository.findById(id);
        
        if (p != null) 
        {
            model.addAttribute("decorativeItem", p);
            return "decorativeItem-edit";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DecorativeItem decorativeItem, Model model) 
    {
        decorativeItemRepository.save(decorativeItem);
        return "redirect:/decorative_item/list";
    }

    @PostMapping("/create")
    public String create(Model model) 
    {
        DecorativeItem decorativeItem = new DecorativeItem("", new ArrayList<Room>());
        model.addAttribute("decorativeItem", decorativeItem);
        return "decorativeItem-create";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Optional<DecorativeItem> p = decorativeItemRepository.findById(id);

        if (p != null) 
        {
            model.addAttribute("decorativeItem", p);
            return "decorativeItem-view";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }
}