package co.edu.javeriana.proyectoWeb.init;

import java.util.ArrayList;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.proyectoWeb.model.DecorativeItem;
import co.edu.javeriana.proyectoWeb.model.Exit;
import co.edu.javeriana.proyectoWeb.model.Item;
import co.edu.javeriana.proyectoWeb.model.Monster;
import co.edu.javeriana.proyectoWeb.model.MonsterType;
import co.edu.javeriana.proyectoWeb.model.Player;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.DecorativeItemRepository;
import co.edu.javeriana.proyectoWeb.repository.ExitRepository;
import co.edu.javeriana.proyectoWeb.repository.ItemRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterTypeRepository;
import co.edu.javeriana.proyectoWeb.repository.PlayerRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;

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
        //ITEMS
        ArrayList<Item> items1 = new ArrayList<>();
        ArrayList<Item> items2 = new ArrayList<>();
        ArrayList<Item> items3 = new ArrayList<>();
        Item item1 = new Item("Dwarf remains","2021-09-24",(long)1,(long)16,"The body of a Dwarf savaged by Goblins.","https://oldschool.runescape.wiki/w/Dwarf_remains");
        itemRepository.save(item1);
        items1.add(item1);
        Item item2 = new Item("Steel arrowtips","2021-08-05",(long)6,(long)0,"I can make some arrows with these.","https://oldschool.runescape.wiki/w/Steel_arrowtips");
        itemRepository.save(item2);
        items1.add(item2);
        Item item3 = new Item("Armadyl mitre","2021-08-05",(long)5000,(long)0.3,"An Armadyl mitre.","https://oldschool.runescape.wiki/w/Armadyl_mitre");
        itemRepository.save(item3);
        items2.add(item3);

        //DECORATIVE ITEMS
        ArrayList<DecorativeItem> decorativeItems1 = new ArrayList<>();
        ArrayList<DecorativeItem> decorativeItems2 = new ArrayList<>();
        DecorativeItem decorativeItem1 = new DecorativeItem("Dwarf multicannon");
        decorativeItemRepository.save(decorativeItem1);
        DecorativeItem decorativeItem2 = new DecorativeItem("Water");
        decorativeItemRepository.save(decorativeItem2);
        DecorativeItem decorativeItem3 = new DecorativeItem("Ice Light");
        decorativeItemRepository.save(decorativeItem3);
        DecorativeItem decorativeItem4 = new DecorativeItem("Pile of skulls");
        decorativeItemRepository.save(decorativeItem4);
        
        //CATEGORY
        ArrayList<String> category1 = new ArrayList<>();
        ArrayList<String> category2 = new ArrayList<>();
        category1.add("Molanisk");
        category2.add("Zombies");

        //MONSTERS
        ArrayList<Monster> monsters1 = new ArrayList<>();
        ArrayList<Monster> monsters2 = new ArrayList<>();
        Monster monster1 = new Monster((long)1000);
        monsters1.add(monster1);
        monsterRepository.save(monster1);
        Monster monster2 = new Monster((long)50);
        monsters2.add(monster2);
        monsterRepository.save(monster2);


        //MONSTERS TYPE
        MonsterType monsterTypes1 = new MonsterType("Molanisk","2021-09-02",(long)40,(long)45,(long)1,(long)52,category1,monsters1,"A strange mole-like being.","https://oldschool.runescape.wiki/w/Molanisk");
        MonsterType monsterTypes2 = new MonsterType("Zombie","2021-09-02",(long)8,(long)0,(long)1,(long)22,category2,monsters2,"Dead man walking.","https://oldschool.runescape.wiki/w/Zombie#Level_13");
        monsterTypeRepository.save(monsterTypes1);
        monsterTypeRepository.save(monsterTypes2);

        //MONSTER TYPE // MONSTER
        monster1.setIdMonsterType(monsterTypes1);
        monsterRepository.save(monster1);
        monster2.setIdMonsterType(monsterTypes2);
        monsterRepository.save(monster2);

        //PLAYER
        ArrayList<Player> players1 = new ArrayList<>();
        ArrayList<Player> players2 = new ArrayList<>();
        Player player1 = new Player("Samy","2021-09-02",(long)40,(long)45,(long)1,(long)52,(long)20,(long)0,(long)100,items1);
        playerRepository.save(player1);
        players1.add(player1);

        //ITEM // PLAYER
        item1.setIdPlayer(player1);
        item2.setIdPlayer(player1);
        itemRepository.save(item1);
        itemRepository.save(item2);


        //EXITS
        ArrayList<Exit> exits1 = new ArrayList<>(); 
        ArrayList<Exit> exits2 = new ArrayList<>(); 


        //ROOMS
        Room room1 = new Room (items2, decorativeItems1, monster1, exits1, players1);
        Room room2 = new Room (items3, decorativeItems2, monster2, exits2, players2);
        roomRepository.save(room1);
        roomRepository.save(room2);

        //MONSTER // ROOM
        monster1.setIdRoom(room1);
        monster2.setIdRoom(room2);
        monsterRepository.save(monster1);
        monsterRepository.save(monster2);

        //PLAYER // ROOM
        player1.setIdRoom(room1);
        playerRepository.save(player1);

    
        //EXITS // ROOM
        Exit exit = new Exit (room1, room2);
        exits1.add(exit);
        exitRepository.save(exit);

        /////////////////////////////////////////////////////////////
        //ArrayList<Item> alI = new ArrayList<>();
        //Item i = new Item ("Casco");
        //alI.add(i);
        //itemRepository.save(i);
        //i = new Item("Zapatos");
        //alI.add(i);
        //itemRepository.save(i);

        //ArrayList<DecorativeItem> alDI = new ArrayList<>();
        //ArrayList<DecorativeItem> alDI2 = new ArrayList<>();
        //DecorativeItem dI = new DecorativeItem ("Lampara");
        //decorativeItemRepository.save(dI);

        
        //MonsterType mT = new MonsterType("Mendieta");
        //monsterTypeRepository.save(mT);
        
       // Monster m = new Monster((long)1200, mT);
        //Monster m2 = new Monster((long)12300, mT);
        //monsterRepository.save(m);
        //monsterRepository.save(m2);

        //ArrayList<Exit> alE = new ArrayList<>();

      //  ArrayList<Player> alP = new ArrayList<>();
        //Player p = new Player("Carlos Escobar");
       // alP.add(p);
        //playerRepository.save(p);

        //Room a = new Room (alI, alDI, m, alE, alP);
        //Room b = new Room (alDI2, m2);
        //roomRepository.save(a);
        //roomRepository.save(b);
        
        //m.setIdRoom(a);
        //m2.setIdRoom(b);
        //monsterRepository.save(m);
        //monsterRepository.save(m2);

        //Exit e = new Exit (a, b);
        //alE.add(e);
        //exitRepository.save(e);

        













        
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