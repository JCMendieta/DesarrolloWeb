package co.edu.javeriana.proyectoWeb.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/list")
    public String itemList(Model model)
    {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "item-list";
    }
}