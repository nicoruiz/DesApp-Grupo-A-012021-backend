package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.TitleNotFoundException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    public List<Title> getAll() {
        return titleRepository.findAll();
    }

    public Title getById(String id) {
        return this.titleRepository.findById(id)
                .orElseThrow(() -> new TitleNotFoundException(id));
    }
}
