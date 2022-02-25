package co.edu.javeriana.proyectoWeb.baseDeDatos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monsters")
public class MonsterController{
    
     @Autowired
     DB db;

     @GetMapping("/list")
     public String displayMonsterList(Model model){
        Collection<Monster> lMonsters = db.findAllMonsters();
        model.addAttribute("monsters", lMonsters);
        return "monster-list";
     }

}
