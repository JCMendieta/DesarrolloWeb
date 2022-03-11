package co.edu.javeriana.proyectoWeb.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/decorative_item")
public class DecorativeItemController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    DecorativeItemRepository decorativeItemRepository;

    @GetMapping("/list")
    public String decorativeItemList(Model model)
    {
        Iterable<DecorativeItem>  decorativeItems = decorativeItemRepository.findAll();
        model.addAttribute("decorativeItems", decorativeItems);
        return "decorativeItem-list";
    }
}