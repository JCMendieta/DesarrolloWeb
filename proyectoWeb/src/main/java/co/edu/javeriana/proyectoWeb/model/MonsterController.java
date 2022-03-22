package co.edu.javeriana.proyectoWeb.model;

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
@RequestMapping("/monster")
public class MonsterController 
{   
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    MonsterTypeRepository monsterTypeRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/list")
    public String list(Model model)
    {
        Iterable<Monster> monsters = monsterRepository.findAll();
        model.addAttribute("monsters", monsters);
        return "monster-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Monster p = monsterRepository.findById(id).get();
        
        if (p != null) 
        {
            model.addAttribute("monster", p);
            return "monster-edit";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id)
    {
        Monster monster =monsterRepository.findById(id).get();
        Room room = monster.getIdRoom();
        if(room != null){
            room.rMonster.setIdRoom(null);
            room.setrMonster(null);
            roomRepository.save(room);
        }
        monsterRepository.delete(monster);
        return "redirect:/monster/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Monster monster, Model model) 
    {
        monsterRepository.save(monster);
        return "redirect:/monster/list";
    }

    @PostMapping("/create")
    public String create(Model model) 
    {
        Monster monster = new Monster ((long)0, new Room (), new MonsterType ());
        model.addAttribute("monster", monster);
        return "monster-create";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) throws NotFoundException 
    {
        Monster p = monsterRepository.findById(id).get();

        if (p != null) 
        {
            Monster monster = monsterRepository.findById(id).get();
            model.addAttribute("monster", monster);
            return "monster-view";
        } 
        else 
        {
            throw new NotFoundException();
        }
    }

    @GetMapping("/room/{id}")
    public String currentItems(Model model, @PathVariable Long id)
    {
        
        Monster m = monsterRepository.findById(id).get();
        Room room = m.idRoom;
        model.addAttribute("room", room);
        return "monster-room";
    }

    @GetMapping("/monster_type/{id}")
    public String monsterType(Model model, @PathVariable Long id)
    {
        Monster monster = monsterRepository.findById(id).get();
        model.addAttribute("monster", monster);
        return "monster-view-monstertype";
    }

    @GetMapping("/monster_type_list/{id}")
    public String itemList (Model model, @PathVariable Long id)
    {
        Iterable<MonsterType> monsterTypes = monsterTypeRepository.findAll();
        Monster m = monsterRepository.findById(id).get();
        model.addAttribute("monsterTypes", monsterTypes);
        model.addAttribute("monster", m);
        return "monster-monstertypes";
    }

    @GetMapping("/monster_type_select/{idMonster}/{idMonsterType}")
    public String addDecorativeItem (@PathVariable Long idMonster, @PathVariable Long idMonsterType)
    {
        Monster m = monsterRepository.findById(idMonster).get();
        MonsterType mt = monsterTypeRepository.findById(idMonsterType).get();
        m.setIdMonsterType(mt);
        monsterRepository.save(m);

        return "redirect:/monster/list";
    }

}
