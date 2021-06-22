package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles.SearchTitleParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles.TitleDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.EntityNotFoundException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.Specifications.TitleSpecsBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq.ConsumerService;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    @Autowired
    private TitleSpecsBuilder titleSpecsBuilder;
    @Autowired
    private ConsumerService consumerService;

    public List<TitleDto> getByCriteria(SearchTitleParamsDto params, Pageable pagingSort) {
        Specification<Title> specs = titleSpecsBuilder.buildCriteriaSpecs(params);
        Page<Title> titles = titleRepository.findAll(specs, pagingSort);

        return Arrays.asList(mapperUtil.getMapper().map(titles.toList(), TitleDto[].class));
    }

    public TitleDto getById(String id) {
        Title title = this.titleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Title", id));

        return mapperUtil.getMapper().map(title, TitleDto.class);
    }

    public void subscribeToTitleNews(String id, String email) {
        Title title = this.titleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Title", id));

        consumerService.subscribe(title.getId(), email);
    }
}
