package co.edu.javeriana.proyectoWeb.api_controller;

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
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

@RestController
@RequestMapping("/monster_api")
public class MonsterApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    MonsterTypeRepository monsterTypeRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    public List<Monster> list(Model model)
    {
        List<Monster> monsters = monsterRepository.findAll();
        model.addAttribute("monsters", monsters);
        return monsters;
    }

    @GetMapping("/edit/{id}")
    public Monster edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Monster p = monsterRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("monster", p);
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
        Monster monster = monsterRepository.findById(id).get();
        Room room = monster.getIdRoom();

        if(room != null)
        {
            room.getrMonster().setIdRoom(null);
            room.setrMonster(null);
            roomRepository.save(room);
        }

        monsterRepository.delete(monster);
    }

    @PostMapping("/save")
    public Monster save(@ModelAttribute Monster monster, Model model) 
    {
        return monsterRepository.save(monster);
    }

    @PostMapping("/create")
    public Monster create(Model model) 
    {
        Monster monster = new Monster ((long)0, new Room (), new MonsterType ());
        model.addAttribute("monster", monster);
        return monster;
    }

    @GetMapping("/view/{id}")
    public Monster view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Monster p = monsterRepository.findById(id).get();

        if (p != null) 
        {
            Monster monster = monsterRepository.findById(id).get();
            model.addAttribute("monster", monster);
            return monster;
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/room/{id}")
    public Room currentRoom(Model model, @PathVariable Long id)
    {
        
        Monster m = monsterRepository.findById(id).get();
        Room room = m.getIdRoom();
        model.addAttribute("room", room);
        return room;
    }

    @GetMapping("/monster_type/{id}")
    public Monster monsterType(Model model, @PathVariable Long id)
    {
        Monster monster = monsterRepository.findById(id).get();
        model.addAttribute("monster", monster);
        return monster;
    }

    //REVISAR TIPO DE RETORNO.
    @GetMapping("/monster_type_list/{id}")
    public List<MonsterType> monsterType_list (Model model, @PathVariable Long id)
    {
        List<MonsterType> monsterTypes = monsterTypeRepository.findAll();
        Monster m = monsterRepository.findById(id).get();
        model.addAttribute("monsterTypes", monsterTypes);
        model.addAttribute("monster", m);
        return monsterTypes;
    }

    @GetMapping("/monster_type_select/{idMonster}/{idMonsterType}")
    public Monster setMonsterTypetoMonster (@PathVariable Long idMonster, @PathVariable Long idMonsterType)
    {
        Monster m = monsterRepository.findById(idMonster).get();
        MonsterType mt = monsterTypeRepository.findById(idMonsterType).get();
        m.setIdMonsterType(mt);
        monsterRepository.save(m);

        return m;
    }
}