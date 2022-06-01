package co.edu.javeriana.proyectoWeb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import co.edu.javeriana.proyectoWeb.model.DecorativeItem;
import co.edu.javeriana.proyectoWeb.model.Exit;
import co.edu.javeriana.proyectoWeb.model.Item;
import co.edu.javeriana.proyectoWeb.model.Monster;
import co.edu.javeriana.proyectoWeb.model.MonsterType;
import co.edu.javeriana.proyectoWeb.model.Player;
import co.edu.javeriana.proyectoWeb.model.Role;
import co.edu.javeriana.proyectoWeb.model.Room;
import co.edu.javeriana.proyectoWeb.repository.DecorativeItemRepository;
import co.edu.javeriana.proyectoWeb.repository.ExitRepository;
import co.edu.javeriana.proyectoWeb.repository.ItemRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterRepository;
import co.edu.javeriana.proyectoWeb.repository.MonsterTypeRepository;
import co.edu.javeriana.proyectoWeb.repository.PlayerRepository;
import co.edu.javeriana.proyectoWeb.repository.RoomRepository;
import co.edu.javeriana.proyectoWeb.service.JwtUserDetailsService;

// mvn test -Dtest=PlayerIntegrationTestController
@ActiveProfiles("playerIntegrationTest")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayerControllerIntegrationTest 
{
	@LocalServerPort
    private int port;

	@Autowired
    private TestRestTemplate REST;

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

	@BeforeEach
	void init ()
	{
		//ITEMS//PLAYER
		ArrayList<Item> items1 = new ArrayList<>();
		ArrayList<Item> items2 = new ArrayList<>();

		Item item1 = new Item("Dwarf remains","2021-09-24",(long)1,(long)16,"The body of a Dwarf savaged by Goblins.","https://oldschool.runescape.wiki/w/Dwarf_remains");
		items1.add(item1);
		itemRepository.save(item1);
		Item item2 = new Item("Steel arrowtips","2021-08-05",(long)6,(long)1,"I can make some arrows with these.","https://oldschool.runescape.wiki/w/Steel_arrowtips");
		items1.add(item2);
		itemRepository.save(item2);
		Item item3 = new Item("Armadyl mitre","2021-08-05",(long)5000,(long)3,"An Armadyl mitre.","https://oldschool.runescape.wiki/w/Armadyl_mitre");
		items1.add(item3);
		itemRepository.save(item3);
		Item item4 = new Item("xd","2021-08-05",(long)5000,(long)4,"An Armadyl mitre.","https://oldschool.runescape.wiki/w/Armadyl_mitre");
		items2.add(item4);
		itemRepository.save(item4);

		//DECORATIVE ITEMS
		ArrayList<DecorativeItem> decorativeItems1 = new ArrayList<>();
		ArrayList<DecorativeItem> decorativeItems2 = new ArrayList<>();
		DecorativeItem decorativeItem1 = new DecorativeItem("Dwarf multicannon");
		decorativeItems1.add(decorativeItem1);
		decorativeItemRepository.save(decorativeItem1);
		DecorativeItem decorativeItem2 = new DecorativeItem("Water");
		decorativeItems1.add(decorativeItem2);
		decorativeItemRepository.save(decorativeItem2);
		DecorativeItem decorativeItem3 = new DecorativeItem("Ice Light");
		decorativeItems2.add(decorativeItem3);
		decorativeItemRepository.save(decorativeItem3);
		DecorativeItem decorativeItem4 = new DecorativeItem("Pile of skulls");
		decorativeItems2.add(decorativeItem4);
		decorativeItemRepository.save(decorativeItem4);

		//CATEGORY
		ArrayList<String> category1 = new ArrayList<>();
		ArrayList<String> category2 = new ArrayList<>();
		category1.add("Molanisk");
		category2.add("Zombies");

		//MONSTERS
		ArrayList<Monster> monsters1 = new ArrayList<>();
		ArrayList<Monster> monsters2 = new ArrayList<>();
		Monster monster1 = new Monster((long)100);
		monsters1.add(monster1);
		monsterRepository.save(monster1);
		Monster monster2 = new Monster((long)50);
		monsters2.add(monster2);
		monsterRepository.save(monster2);

		//MONSTERS TYPE
		MonsterType monsterTypes1 = new MonsterType("Molanisk","2021-09-02",(long)50,(long)0,(long)1,(long)100,category1,monsters1,"A strange mole-like being.","https://oldschool.runescape.wiki/w/Molanisk");
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
		Player player1 = new Player("Samy", "xd", "2021-09-02",(long)50,(long)0,(long)1,(long)100,(long)20,(long)0,(long)20, null, Role.ROLE_DESIGNER,(long)0);
		playerRepository.save(player1);
		players1.add(player1);
		Player player2 = new Player("Mendieta", "dx", "2022-10-03",(long)62,(long)78,(long)2,(long)12,(long)4,(long)0,(long)20, items2, Role.ROLE_ADMIN,(long)0);
		playerRepository.save(player2);
		players2.add(player2);
		Player player3 = new Player("Carlos", "a", "2022-10-03",(long)62,(long)78,(long)2,(long)12,(long)4,(long)0,(long)20, items2, Role.ROLE_PLAYER,(long)0);
		playerRepository.save(player3);
		players1.add(player3);

		//EXITS
		ArrayList<Exit> exits1 = new ArrayList<>(); 
		ArrayList<Exit> exits2 = new ArrayList<>(); 
		ArrayList<Exit> exits3 = new ArrayList<>(); 

		//ROOMS
		Room room1 = new Room (items1, decorativeItems1, null, exits1, players1);
		Room room2 = new Room (items2, decorativeItems2, monster2, exits2, players2);
		Room room3 = new Room(null, null, null, exits3, null);
		roomRepository.save(room1);
		roomRepository.save(room2);
		roomRepository.save(room3);

		//MONSTER // ROOM
		monster1.setIdRoom(room1);
		monster2.setIdRoom(room2);
		monsterRepository.save(monster1);
		monsterRepository.save(monster2);

		//PLAYER // ROOM
		player1.setIdRoom(room1);
		playerRepository.save(player1);
		player2.setIdRoom(room2);
		playerRepository.save(player2);
		player3.setIdRoom(room1);
		playerRepository.save(player3);

		//EXITS // ROOM
		Exit exit1 = new Exit (room1, room2);
		exits1.add(exit1);
		exitRepository.save(exit1);
		Exit exit2 = new Exit (room1, room3);
		exits1.add(exit2);
		exitRepository.save(exit2);
		Exit exit3 = new Exit (room2, room1);
		exits2.add(exit3);
		exitRepository.save(exit3);
		Exit exit4 = new Exit (room3, room1);
		exits3.add(exit4);
		exitRepository.save(exit4);
		
		PlayerSystemTest a = new PlayerSystemTest();
		a.login();
	}

	@Test
    void findPlayerByUsername() throws Exception 
	{
        Player player = REST.getForObject("http://localhost:" + port + "/player_api/Samy/xd", Player.class);
		
        assertEquals("Samy", player.getName());
		assertEquals("xd", player.getPassword());
    }

	@Test
    void spawn() throws Exception 
	{
        Player player = REST.getForObject("http://localhost:" + port + "/player_api/spawn/13", Player.class);

        assertEquals((long)13, player.getId());
		assertEquals((long)16, player.getIdRoom().getId());
		assertEquals((long)0, player.getClock());
		assertEquals((long)40, player.getHitpoints());
		assertEquals(new ArrayList<Item>(), player.getItems());
    }

	@Test
    void collect() throws Exception 
	{
        Player player = REST.getForObject("http://localhost:" + port + "/player_api/collect/13/1", Player.class);
		Item item = itemRepository.getById((long)1);
		Boolean isPlayerItem = false;
		Room room = roomRepository.getById(player.getIdRoom().getId());

		for (Item i : player.getItems()) 
		{
			if (i.getId().equals(item.getId()))
			{
				isPlayerItem = true;
			}
		}

		assertEquals(isPlayerItem, true);
		assertEquals(item.getIdPlayer().getId(), player.getId());
		assertEquals(item.getIdRoom().contains(room), false);
		assertEquals(room.getrItems().contains(item), false);
    }

	@Test
    void discard() throws Exception 
	{
        Player player = REST.getForObject("http://localhost:" + port + "/player_api/discard/14/4", Player.class);
		Item item = itemRepository.getById((long)4);
		Boolean isRoomItem = false, isItemRoom = false;
		Room room = roomRepository.getById(player.getIdRoom().getId());

		for (Item i : room.getrItems()) 
		{
			if (i.getId().equals(item.getId()))
			{
				isRoomItem = true;
			}
		}

		for (Room r : item.getIdRoom()) 
		{
			if (r.getId().equals(room.getId()))
			{
				isItemRoom = true;
			}
		}

		assertEquals(isRoomItem, true);
		assertEquals(isItemRoom, true);
		assertEquals(player.getItems().contains(item), false);
		assertEquals(item.getIdPlayer(), null);
    }

	@Test
    void move() throws Exception 
	{
        Player player = REST.getForObject("http://localhost:" + port + "/player_api/move/13/19", Player.class);
		Exit exit = exitRepository.getById((long)19);
		Boolean isOnNextRoom = false;

		for (Player p : exit.getIdSRoom().getrPlayers()) 
		{
			if (p.getId().equals(player.getId()))
			{
				isOnNextRoom = true;
			}
		}

		
		assertEquals(isOnNextRoom, true);
		assertEquals(player.getIdRoom().getId(), exit.getIdSRoom().getId());
		assertEquals(exit.getIdFRoom().getrPlayers().contains(player), false);
    }

	@Test
    void players() throws Exception 
	{
		ResponseEntity<List> response = REST.getForEntity("http://localhost:" + port + "/player_api/currentPlayers/16", List.class);
		
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

	@Test
    void finish() throws Exception 
	{
        Long score = REST.getForObject("http://localhost:" + port + "/player_api/finish/14", Long.class);
		List<Item> items = playerRepository.findById((long)14).get().getItems();
		Long playerScore = (long)0;

		for (Item item : items) 
		{
			playerScore = playerScore + item.getCost();
		}

		assertEquals(playerScore, score);
    }

	@Test
    void attack() throws Exception 
	{
        Player playerT = REST.getForObject("http://localhost:" + port + "/player_api/attack/13/9", Player.class);
		Room room = roomRepository.getById(playerT.getIdRoom().getId());
		Monster monster = monsterRepository.getById((long)9);


		assertNotEquals((long)100, playerT.getHitpoints());
		assertNotEquals((long)100, monster.getHitpoints());
		
    }

}		