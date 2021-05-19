package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.TitleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/titles")
    public ResponseEntity<List<Title>> getTitles() {
        List<Title> titles = this.titleService.getAll();
        return ResponseEntity.ok().body(titles);
    }

    @GetMapping("/titles/{id}")
    public ResponseEntity<Title> getTitle(@PathVariable String id) {
        Title title = this.titleService.getById(id);
        return ResponseEntity.ok().body(title);
    }
}
