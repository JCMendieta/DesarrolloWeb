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
    RoomRepository roomRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    DecorativeItemRepository decorativeItemRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception
    {
        Monster m = new Monster("Mendieta", (long)1);
        Monster m2 = new Monster("Samy", (long)2);
        monsterRepository.save(m);
        monsterRepository.save(m2);

        roomRepository.save(new Room(m));
        roomRepository.save(new Room(m2));

        itemRepository.save(new Item("Mesa"));
        itemRepository.save(new Item("Silla"));

        //decorativeItemRepository.save(new DecorativeItem("Flor"));

        Room r = null;

        for (Room room : roomRepository.findAll()) 
        {
            if (room.getrMonster() == m)
            {
                r = room;
            }
        }

        for (Item item : itemRepository.findAll()) 
        {
            item.setIdRoom(r);
            itemRepository.save(item);
        }

        /*for (DecorativeItem decorativeItem : decorativeItemRepository.findAll()) 
        {
            //decorativeItem.setIdRoom(r);
            decorativeItemRepository.save(decorativeItem);
        }*/
    }
}