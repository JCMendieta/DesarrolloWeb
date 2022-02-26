package co.edu.javeriana.proyectoWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ProyectoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoWebApplication.class, args);
	}
}
