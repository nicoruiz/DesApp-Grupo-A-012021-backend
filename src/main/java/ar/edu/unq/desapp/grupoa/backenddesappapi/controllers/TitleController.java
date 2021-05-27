package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.TitleDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.TitleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@Api(tags = "Titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/titles")
    public ResponseEntity<List<TitleDto>> getTitles() {
        return ResponseEntity.ok().body(this.titleService.getAll());
    }

    @GetMapping("/titles/{id}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable String id) {
        return ResponseEntity.ok().body(titleService.getById(id));
    }
}
