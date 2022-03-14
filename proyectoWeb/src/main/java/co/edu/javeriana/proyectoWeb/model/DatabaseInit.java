package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements ApplicationRunner
{
    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    MonsterTypeRepository monsterTypeRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    DecorativeItemRepository decorativeItemRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ExitRepository exitRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception
    {
        ArrayList<Item> alI = new ArrayList<>();
        Item i = new Item ("Dildo");
        alI.add(i);
        itemRepository.save(i);

        ArrayList<DecorativeItem> alDI = new ArrayList<>();
        ArrayList<DecorativeItem> alDI2 = new ArrayList<>();
        DecorativeItem dI = new DecorativeItem ("Lampara");
        alDI.add(dI);
        decorativeItemRepository.save(dI);

        
        MonsterType mT = new MonsterType("Mendieta");
        monsterTypeRepository.save(mT);
        
        Monster m = new Monster((long)1200, mT);
        monsterRepository.save(m);

        ArrayList<Exit> alE = new ArrayList<>();

        ArrayList<Player> alP = new ArrayList<>();
        Player p = new Player("Carlos Escobar");
        alP.add(p);
        playerRepository.save(p);

        Room a = new Room (alI, alDI, m, alE, alP);
        Room b = new Room (alDI2, m);
        roomRepository.save(a);
        roomRepository.save(b);
        
        m.setIdRoom(a);

        Exit e = new Exit (a, b);
        alE.add(e);
        exitRepository.save(e);

        













        
        /*Monster m2 = new Monster((long)5000);
        //monsterRepository.save(m);
        monsterRepository.save(m2);

        monsterTypeRepository.save(new MonsterType("Mendieta"));
        monsterTypeRepository.save(new MonsterType("Samy"));

        roomRepository.save(new Room(m));
        roomRepository.save(new Room(m2));

        itemRepository.save(new Item("Mesa"));
        itemRepository.save(new Item("Silla"));

        decorativeItemRepository.save(new DecorativeItem("Flor"));
        decorativeItemRepository.save(new DecorativeItem("Pintura"));

        playerRepository.save(new Player("Carlos Escobar"));
        playerRepository.save(new Player("Camilo Hernández"));

        /*Room r = null;

        for (Room room : roomRepository.findAll()) 
        {
            if (room.getrMonster() == m)
            {
                r = room;
            }
        }*/
    }
}