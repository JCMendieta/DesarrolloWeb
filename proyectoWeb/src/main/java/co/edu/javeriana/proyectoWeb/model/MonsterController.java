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
@RequestMapping("/monster")
public class MonsterController 
{   
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MonsterRepository monsterRepository;

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
        Optional<Monster> p = monsterRepository.findById(id);
        
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
        Optional<Monster> p = monsterRepository.findById(id);

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
}
