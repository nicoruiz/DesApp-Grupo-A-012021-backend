package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.CreatePremiumReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.CreateUserReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.UserReview;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.MapperUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews() {
        List<ReviewDto> reviews = this.reviewService.getAll();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/reviews/{titleId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByTitle(@PathVariable String titleId) {
        List<ReviewDto> reviews = this.reviewService.getByTitle(titleId);
        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping(value = "/reviews/{titleId}/user")
    public ResponseEntity<ReviewDto> createUserReview(@RequestBody CreateUserReviewDto createReviewDto, @PathVariable String titleId) {
        Review newReview = mapperUtil.getMapper().map(createReviewDto, UserReview.class);

        ReviewDto created = this.reviewService.create(newReview, titleId);
        return ResponseEntity.ok().body(created);
    }

    @PostMapping(value = "/reviews/{titleId}/premium")
    public ResponseEntity<ReviewDto> createPremiumReview(@RequestBody CreatePremiumReviewDto createReviewDto, @PathVariable String titleId) {
        Review newReview = mapperUtil.getMapper().map(createReviewDto, PremiumReview.class);

        ReviewDto created = this.reviewService.create(newReview, titleId);
        return ResponseEntity.ok().body(created);
    }
}
