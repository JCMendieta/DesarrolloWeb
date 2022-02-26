package co.edu.javeriana.proyectoWeb.model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/monster")
public class MonsterController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MonsterRepository monsterRepository;

    @GetMapping("/list")
    String findAll ()
    {
        Iterable<Monster> monsters = monsterRepository.findAll();

        for (Monster monster : monsters)
        {
            log.info("{}", monster.getName());
        }

        return "monster-list";
    }
}
