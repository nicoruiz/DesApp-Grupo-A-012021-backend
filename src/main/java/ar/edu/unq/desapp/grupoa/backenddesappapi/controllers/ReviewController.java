package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.CreateReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.MapperUtil;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.SortHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@Api(tags = "Reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MapperUtil mapperUtil;
    @Autowired
    private SortHelper sortHelper;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "createdOn,desc") String[] sort)
    {
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortHelper.getSort(sort)));

        return ResponseEntity.ok().body(reviewService.getAll(pagingSort));
    }

    @GetMapping("/reviews/{titleId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByTitle(
            @PathVariable String titleId,
            @RequestParam(required = false) String platform,
            @RequestParam(required = false) Boolean spoiler,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String localization,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "createdOn,desc") String[] sort)
    {
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortHelper.getSort(sort)));

        return ResponseEntity.ok().body(reviewService.getByCriteria(titleId, platform, spoiler, type, language, localization, pagingSort));
    }

    @PostMapping(value = "/reviews/{titleId}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody CreateReviewDto createReviewDto, @PathVariable String titleId) {
        return ResponseEntity.ok().body(reviewService.create(createReviewDto, titleId));
    }
}
