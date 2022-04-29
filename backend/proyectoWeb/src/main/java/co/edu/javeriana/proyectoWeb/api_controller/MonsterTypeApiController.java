package co.edu.javeriana.proyectoWeb.api_controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.javeriana.proyectoWeb.model.Monster;
import co.edu.javeriana.proyectoWeb.model.MonsterType;
import co.edu.javeriana.proyectoWeb.repository.MonsterRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterTypeRepository;

@RestController
@RequestMapping("/monster_type_api")
public class MonsterTypeApiController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    MonsterTypeRepository monsterTypeRepository;

    @GetMapping("/currentMonsterType/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional
    public MonsterType monsterType (@PathVariable Long id)
    {
        Monster monster = monsterRepository.findById(id).get();
        MonsterType monsterType = null;

        for (MonsterType mt : monsterTypeRepository.findAll())
        {
            for (Monster m : mt.getMonsters())
            {
                if(m.equals(monster))
                {
                    monsterType = mt;
                }
            }  
        }

        return monsterType;
    }
}