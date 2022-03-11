package co.edu.javeriana.proyectoWeb.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exit")
public class ExitController 
{   
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ExitRepository exitRepository;

    //@GetMapping("/list")
}