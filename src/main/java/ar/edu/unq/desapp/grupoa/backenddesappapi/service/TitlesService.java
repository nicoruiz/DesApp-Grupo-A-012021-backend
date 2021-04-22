package ar.edu.unq.desapp.grupoa.backenddesappapi.service;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TitlesService {
    /*@Autowired
    private TitlesRepository titlesRepository;

    public List<Title> getAll() {
        return (List<Title>) this.titlesRepository.findAll();
    }*/

    public List<Title> getAllDummy() {
        List<String> genres = Arrays.asList("Comedy", "Drama");
        Title dummy = new Title("tt001", "Movie", "Pulp Fiction", "Pulp Fiction", true, new Date(), new Date());
        return Arrays.asList(dummy, dummy);
    }
}
