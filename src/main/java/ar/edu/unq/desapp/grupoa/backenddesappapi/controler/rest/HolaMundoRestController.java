package ar.edu.unq.desapp.grupoa.backenddesappapi.controler.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRestController {
    
    @GetMapping("/api/hola")
    public String holaMundo() {
        return "Hola Mundo!";
    }
}
