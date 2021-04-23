package ar.edu.unq.desapp.grupoa.backenddesappapi.service;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TitleService implements ITitleService{
    @Autowired
    private TitleRepository repository;

    public List<Title> getAll() {
        return (List<Title>) this.repository.findAll();
    }
}
