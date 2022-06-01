package co.edu.javeriana.proyectoWeb;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.KeySelector;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import co.edu.javeriana.proyectoWeb.model.*;
import co.edu.javeriana.proyectoWeb.repository.*;

@ActiveProfiles("systemtest")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class PlayerSystemTest 
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

    private ChromeDriver driver;
    private WebDriverWait wait;
    private String frontend_url;
	private String backend_url;

	@BeforeEach
    void init() 
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
		Player player1 = new Player("Samy", "xd", "2021-09-02",(long)40,(long)6,(long)1,(long)40,(long)20,(long)0,(long)20, null, Role.ROLE_DESIGNER,(long)0);
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

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, 5);
        this.frontend_url = "http://localhost:4200";
		this.backend_url = "http://localhost:8080";
        login();
    }

    @AfterEach
    void end() 
    {
        driver.quit();
    }

	@Test
	void app()
	{
		driver.get(frontend_url);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("router")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("router")));
	}

    @Test
    void login() 
    {
        driver.get(frontend_url);

        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-success")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("input-login")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-success")));
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-success")));
		wait.until(ExpectedConditions.textToBe(By.className("btn-success"), "Login"));
	
        username.sendKeys("Samy");
        password.sendKeys("xd");
        button.click();
        wait.until(ExpectedConditions.urlToBe(frontend_url + "/home"));
    }

    @Test
    void home()
    {
		driver.get(frontend_url + "/home");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("text")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("text")));
        wait.until(ExpectedConditions.textToBe(By.id("title"), "MULTI USER DUNGEON"));
        wait.until(ExpectedConditions.textToBe(By.id("paragraph"), 
        "Un MUD es un juego multijugador donde los participantes exploran un calabozo con monstruos y tesoros. El objetivo es recolectar la mayor cantidad de tesoros, sin morir, en un tiempo determinado."));
        wait.until(ExpectedConditions.textToBe(By.id("menu"), "Menu"));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("role")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("playerAndDesigner")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("designer")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("admin")));

		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("play")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("play")));
		wait.until(ExpectedConditions.textToBe(By.id("play"), "PLAY"));

		button.click();
		wait.until(ExpectedConditions.urlToBe(frontend_url + "/game"));

		driver.get(frontend_url + "/home");

		WebElement linkToRooms = wait.until(ExpectedConditions.elementToBeClickable(By.id("rooms")));
		linkToRooms.click();
		wait.until(ExpectedConditions.urlToBe(backend_url + "/room/list"));
		driver.get(frontend_url + "/home");

		WebElement linkToMonsters = wait.until(ExpectedConditions.elementToBeClickable(By.id("monsters")));
		linkToMonsters.click();
		wait.until(ExpectedConditions.urlToBe(backend_url + "/monster/list"));
		driver.get(frontend_url + "/home");

		WebElement linkToMonsterTypes = wait.until(ExpectedConditions.elementToBeClickable(By.id("monsterTypes")));
		linkToMonsterTypes.click();
		wait.until(ExpectedConditions.urlToBe(backend_url + "/monster_type/list"));
		driver.get(frontend_url + "/home");

		WebElement linkToItems = wait.until(ExpectedConditions.elementToBeClickable(By.id("items")));
		linkToItems.click();
		wait.until(ExpectedConditions.urlToBe(backend_url + "/item/list"));
		driver.get(frontend_url + "/home");

		WebElement linkToDecorativeItems = wait.until(ExpectedConditions.elementToBeClickable(By.id("decorativeItems")));
		linkToDecorativeItems.click();
		wait.until(ExpectedConditions.urlToBe(backend_url + "/decorative_item/list"));
    }

	@Test
	void game()
	{
		driver.get(frontend_url + "/game");

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("player")));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("text")));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("button")));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("vText")));
		List<WebElement> currentPlayers = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("currentPlayers")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("player")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("text")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("vText")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("currentPlayers")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("discard")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("collect")));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.id("collect"), 3));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("move")));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.id("move"), 2));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("attack")));
		wait.until(ExpectedConditions.textToBe(By.id("playerName"), "Player name : Samy"));
		wait.until(ExpectedConditions.textToBe(By.id("playerHP"), "Player hitpoints : 40"));
		wait.until(ExpectedConditions.textToBe(By.id("playerAL"), "Player attack level : 40"));
		wait.until(ExpectedConditions.textToBe(By.id("playerDS"), "Player defense slash : 6"));
		wait.until(ExpectedConditions.textToBe(By.id("playerWC"), "Player weight capacity : 20"));
		wait.until(ExpectedConditions.textToBe(By.id("playerAW"), "Player actual weight : 0"));
		wait.until(ExpectedConditions.textToBe(By.id("playerCC"), "Clock counter : 0"));
		wait.until(ExpectedConditions.textToBe(By.id("playerMT"), "Max time : 20"));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle"), "Your items:"));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle2"), "Items in the room:"));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle3"), "Decorative items:"));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle4"), "Exits"));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle5"), "Refresh the page to see actual monster stats and actual players in the room."));
		wait.until(ExpectedConditions.textToBe(By.id("noMonsters"), "There's no monster in this room."));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle7"), "Players in the room"));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle8"), "You"));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle9"), "LogBook"));

		WebElement collect = wait.until(ExpectedConditions.elementToBeClickable(By.id("collect")));

		collect.click();
		wait.until(ExpectedConditions.textToBe(By.id("playerAW"), "Player actual weight : 16"));
		collect = wait.until(ExpectedConditions.elementToBeClickable(By.id("collect")));
		collect.click();
		wait.until(ExpectedConditions.textToBe(By.id("playerAW"), "Player actual weight : 17"));
		collect = wait.until(ExpectedConditions.elementToBeClickable(By.id("collect")));
		collect.click();
		wait.until(ExpectedConditions.textToBe(By.id("playerAW"), "Player actual weight : 20"));

		WebElement discard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discard")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("discard")));
		discard.click();
		wait.until(ExpectedConditions.textToBe(By.id("playerAW"), "Player actual weight : 4"));
		discard = wait.until(ExpectedConditions.elementToBeClickable(By.id("discard")));
		discard.click();
		wait.until(ExpectedConditions.textToBe(By.id("playerAW"), "Player actual weight : 3"));
		discard = wait.until(ExpectedConditions.elementToBeClickable(By.id("discard")));
		discard.click();
		wait.until(ExpectedConditions.textToBe(By.id("playerAW"), "Player actual weight : 0"));

		WebElement move = wait.until(ExpectedConditions.elementToBeClickable(By.id("move")));
		move.click();
		wait.until(ExpectedConditions.textToBe(By.id("playerCC"), "Clock counter : 1"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtitle6")));
		wait.until(ExpectedConditions.textToBe(By.id("subtitle6"), "Monster"));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("monster")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("monster")));


		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("logbook")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("logbook")));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.textToBe(By.id("monsterName"), "Name: Zombie"));
		wait.until(ExpectedConditions.textToBe(By.id("monsterHP"), "Hitpoints: 50"));
		wait.until(ExpectedConditions.textToBe(By.id("monsterAL"), "Attack level: 8"));
		wait.until(ExpectedConditions.textToBe(By.id("monsterDS"), "Defense slash: 0"));
		wait.until(ExpectedConditions.textToBe(By.id("monsterSize"), "Size: 1"));

		WebElement attack = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attack")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("attack")));
		attack.click();
	}

	@Test
	void finish()
	{
		driver.get(frontend_url + "/finish");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("end")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("accept")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("end")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accept")));
		wait.until(ExpectedConditions.textToBe(By.id("end"), "The game has ended, your score is:"));

		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("accept")));
		button.click();
		wait.until(ExpectedConditions.urlToBe(frontend_url + "/"));
	}
}