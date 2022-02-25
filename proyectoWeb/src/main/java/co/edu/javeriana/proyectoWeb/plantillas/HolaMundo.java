package co.edu.javeriana.proyectoWeb.plantillas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plantillas")
public class HolaMundo {
    @GetMapping("/hola")
    public String hola() {
        return "hola-mundo";
    }
    
}
