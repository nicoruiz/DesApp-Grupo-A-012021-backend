package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.TitleDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.EntityNotFoundException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private MapperUtil mapperUtil;

    public List<TitleDto> getAll() {
        List<Title> titles = titleRepository.findAll();

        return Arrays.asList(mapperUtil.getMapper().map(titles, TitleDto[].class));
    }

    public TitleDto getById(String id) {
        Title title = this.titleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Title.class.getSimpleName(), id));

        return mapperUtil.getMapper().map(title, TitleDto.class);
    }
}
