package co.edu.javeriana.proyectoWeb.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monster_type")
public class MonsterTypeController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MonsterTypeRepository monsterTypeRepository;

    @GetMapping("/list")
    public String monsterTypeList(Model model)
    {
        Iterable<MonsterType> monsterTypes = monsterTypeRepository.findAll();
        model.addAttribute("monsterTypes", monsterTypes);
        return "monsterType-list";
    }

    
}
