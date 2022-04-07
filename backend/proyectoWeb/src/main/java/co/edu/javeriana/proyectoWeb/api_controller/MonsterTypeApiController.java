package co.edu.javeriana.proyectoWeb.api_controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.proyectoWeb.model.Monster;
import co.edu.javeriana.proyectoWeb.model.MonsterType;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.MonsterRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterTypeRepository;

@RestController
@RequestMapping("/monster_type_api")
public class MonsterTypeApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MonsterTypeRepository monsterTypeRepository;

    @Autowired 
    MonsterRepository monsterRepository;

    @GetMapping("/list")
    public List<MonsterType> list(Model model)
    {
        List<MonsterType> monsterTypes = monsterTypeRepository.findAll();
        model.addAttribute("monsterTypes", monsterTypes);
        return monsterTypes;
    }

    @GetMapping("/edit/{id}")
    public MonsterType edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        MonsterType p = monsterTypeRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("monsterType", p);
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
        MonsterType monsterType=monsterTypeRepository.findById(id).get();

        for (Monster monster : monsterType.getMonsters())
        {
            Room r = monster.getIdRoom();
            monster.unlinkMonster(r);
            monsterRepository.delete(monster);
        }

        monsterTypeRepository.deleteById(id);
    }

    @PostMapping("/save")
    public MonsterType save(@ModelAttribute MonsterType monsterType, Model model) 
    {
        return monsterTypeRepository.save(monsterType);
    }

    @PostMapping("/create")
    public MonsterType create(Model model) 
    {
        MonsterType monsterType = new MonsterType("", "", (long)0, (long)0, (long)0, (long)0, new ArrayList<String>(), new ArrayList<Monster>(), "", "");
        model.addAttribute("monsterType", monsterType);
        return monsterType;
    }

    @GetMapping("/view/{id}")
    public MonsterType view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        MonsterType p = monsterTypeRepository.findById(id).get();

        if (p != null) 
        {
            MonsterType monsterType = monsterTypeRepository.findById(id).get();
            model.addAttribute("monsterType", monsterType);
            return monsterType;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }
    @GetMapping("view_monsters_list/{id}")
    public List<Monster> viewMonsters(Model model, @PathVariable Long id)
    {
        MonsterType monsterType = monsterTypeRepository.findById(id).get();
        List<Monster> monsters = monsterType.getMonsters();
        model.addAttribute("monsters", monsters);
        return monsters;
    }
}