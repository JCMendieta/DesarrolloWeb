package co.edu.javeriana.proyectoWeb.model;

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

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception
    {
        Monster m = new Monster((long)1200);
        Monster m2 = new Monster((long)5000);
        monsterRepository.save(m);
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