package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.PageConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.config.SortConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.titles.CreateSubscriptionDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.titles.SearchTitleParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.titles.TitleDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.titles.TitleResumeDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.TitleService;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.sorting.TitleSortHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

@RestController
@EnableAutoConfiguration
@Api(tags = "Titles")
public class TitleController {

    @Autowired
    private TitleService titleService;
    @Autowired
    private TitleSortHelper sortHelper;

    @GetMapping("/titles")
    public ResponseEntity<List<TitleDto>> getTitles(
            @RequestParam(required = false) String titleName,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer decade,
            @RequestParam(required = false) Double rating,
            @RequestParam(required = false) String personName,
            @RequestParam(defaultValue = PageConfig.PAGE) int page,
            @RequestParam(defaultValue = PageConfig.TITLE_PAGE_SIZE) int size,
            @RequestParam(defaultValue = SortConfig.TITLE_DEFAULT) String[] sort
    ) {
        SearchTitleParamsDto params = new SearchTitleParamsDto(titleName, genre, decade, rating, personName);
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortHelper.getSort(sort)));

        return ResponseEntity.ok().body(this.titleService.getByCriteria(params, pagingSort));
    }

    @PostMapping(value = "/titles/{id}/subscription")
    public ResponseEntity<String> subscribeToTitleReviews(
            @PathVariable String id,
            @Valid
            @RequestBody CreateSubscriptionDto createSubscriptionDto
    ) {
        titleService.subscribeToTitleNews(id, createSubscriptionDto.getEmail());
        return ResponseEntity.ok().body("Subscribed!");
    }
    
    @GetMapping("/titles/{id}")
    @Cacheable(value="title", key="#id")
    public TitleResumeDto getTitleResume(@PathVariable String id) {
        return titleService.getById(id);
    }
}
