package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.PageConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.config.SortConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles.SearchTitleParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles.TitleDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.TitleService;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.sorting.TitleSortHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Boolean topRated,
            @RequestParam(required = false) String personName,
            @RequestParam(defaultValue = PageConfig.PAGE) int page,
            @RequestParam(defaultValue = PageConfig.TITLE_PAGE_SIZE) int size,
            @RequestParam(defaultValue = SortConfig.TITLE_DEFAULT) String[] sort
    ) {
        SearchTitleParamsDto params = new SearchTitleParamsDto(titleName, genre, year, topRated, personName);
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortHelper.getSort(sort)));

        return ResponseEntity.ok().body(this.titleService.getByCriteria(params, pagingSort));
    }

    @GetMapping("/titles/{id}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable String id) {
        return ResponseEntity.ok().body(titleService.getById(id));
    }
}
