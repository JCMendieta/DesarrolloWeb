package co.edu.javeriana.proyectoWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;

import co.edu.javeriana.proyectoWeb.baseDeDatos.DB;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ProyectoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoWebApplication.class, args);
	}

	@Bean
	public DB createDB(){
		return new DB();
	}
}
