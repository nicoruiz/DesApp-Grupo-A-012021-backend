package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.PageConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.config.SortConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.CreateReportDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.CreateReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.SearchReviewParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.BadWordsReport;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.NoSenseReport;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.OffensiveReport;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.SpoilerReport;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.MapperUtil;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.sorting.ReviewSortHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@EnableAutoConfiguration
@Api(tags = "Reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewSortHelper sortHelper;
    @Autowired
    private MapperUtil mapperUtil;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(
            @RequestParam(defaultValue = PageConfig.PAGE) int page,
            @RequestParam(defaultValue = PageConfig.REVIEW_PAGE_SIZE) int size,
            @RequestParam(defaultValue = SortConfig.REVIEW_DEFAULT) String[] sort
    ) {
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
            @RequestParam(defaultValue = PageConfig.PAGE) int page,
            @RequestParam(defaultValue = PageConfig.REVIEW_PAGE_SIZE) int size,
            @RequestParam(defaultValue = SortConfig.REVIEW_DEFAULT) String[] sort
    ) {
        SearchReviewParamsDto params = new SearchReviewParamsDto(titleId, platform, spoiler, type, language, localization);
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortHelper.getSort(sort)));

        return ResponseEntity.ok().body(reviewService.getByCriteria(params, pagingSort));
    }

    @PostMapping(value = "/reviews/{titleId}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody CreateReviewDto createReviewDto, @PathVariable String titleId) throws IOException {
        return ResponseEntity.ok().body(reviewService.create(createReviewDto, titleId));
    }

    @PostMapping(value = "/reviews/{reviewId}/dislike")
    public ResponseEntity<ReviewDto> dislike(@PathVariable long reviewId) {
        return ResponseEntity.ok().body(reviewService.dislike(reviewId));
    }

    @PostMapping(value = "/reviews/{reviewId}/spoiler")
    public ResponseEntity<ReviewDto> reportSpoiler(@RequestBody CreateReportDto reportDto, @PathVariable long reviewId) {
        SpoilerReport report = mapperUtil.getMapper().map(reportDto, SpoilerReport.class);
        return ResponseEntity.ok().body(reviewService.report(reviewId, report));
    }
    
    @PostMapping(value = "/reviews/{reviewId}/bad-words")
    public ResponseEntity<ReviewDto> reportBadWords(@RequestBody CreateReportDto reportDto, @PathVariable long reviewId) {
        BadWordsReport report = mapperUtil.getMapper().map(reportDto, BadWordsReport.class);
        return ResponseEntity.ok().body(reviewService.report(reviewId, report));
    }
    
    @PostMapping(value = "/reviews/{reviewId}/offensive")
    public ResponseEntity<ReviewDto> reportOffensive(@RequestBody CreateReportDto reportDto, @PathVariable long reviewId) {
        OffensiveReport report = mapperUtil.getMapper().map(reportDto, OffensiveReport.class);
        return ResponseEntity.ok().body(reviewService.report(reviewId, report));
    }
    
    @PostMapping(value = "/reviews/{reviewId}/no-sense")
    public ResponseEntity<ReviewDto> reportNoSense(@RequestBody CreateReportDto reportDto, @PathVariable long reviewId) {
        NoSenseReport report = mapperUtil.getMapper().map(reportDto, NoSenseReport.class);
        return ResponseEntity.ok().body(reviewService.report(reviewId, report));
    }
}
