package ar.edu.unq.desapp.grupoa.backenddesappapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRestController {
    
    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo!";
    }
}
