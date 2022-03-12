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

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Optional<Item> p = itemRepository.findById(id);
        
        if (p != null) 
        {
            model.addAttribute("item", p);
            return "item-edit";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) throws NotFoundException 
    {
        
        
        itemRepository.deleteById(id);
        return "redirect:/item/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Item item, Model model) 
    {
        itemRepository.save(item);
        return "redirect:/item/list";
    }
}