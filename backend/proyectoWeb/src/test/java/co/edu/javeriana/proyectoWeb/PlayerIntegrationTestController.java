package co.edu.javeriana.proyectoWeb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.javeriana.proyectoWeb.model.Item;
import co.edu.javeriana.proyectoWeb.model.Player;
import co.edu.javeriana.proyectoWeb.model.Role;
import co.edu.javeriana.proyectoWeb.repository.PlayerRepository;

// mvn test -Dtest=PlayerIntegrationTestController
@ActiveProfiles("integrationtest")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayerIntegrationTestController 
{
	@LocalServerPort
    private int port;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
    private TestRestTemplate REST;

	@BeforeEach
	void init ()
	{
		playerRepository.save(new Player("Carlos_Erazo", "123"));
		playerRepository.save(new Player("vins", "321"));
		playerRepository.save(new Player("Julio", "000"));
	}

	//Toca ver si funciona.
	@Test
    void testFindPlayerByUsername() throws Exception 
	{
        Player player = REST.getForObject("http://localhost:" + port + "/player_api/vins/321", Player.class);
        assertEquals("vins", player.getName());
		assertEquals("321", player.getPassword());
    }
}
