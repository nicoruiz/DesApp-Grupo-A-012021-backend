package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/titles")
    public List<Title> getTitles() {
        return this.titleService.getAll();
    }

    @GetMapping("/titles/{id}")
    public Title getTitle(@PathVariable String id) {
        return this.titleService.getById(id);
    }
}
