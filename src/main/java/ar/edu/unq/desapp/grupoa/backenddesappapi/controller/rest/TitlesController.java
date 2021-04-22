package ar.edu.unq.desapp.grupoa.backenddesappapi.controller.rest;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.service.TitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TitlesController {
    @Autowired
    private TitlesService titlesService;

    @GetMapping("/titles")
    public List<Title> getTitles() {
        return this.titlesService.getAllDummy();
    }
}
