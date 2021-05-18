package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.UserReview;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.ReviewService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api( tags = "Reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews() {
        List<Review> reviews = this.reviewService.getAll();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/reviews/{titleId}")
    public ResponseEntity<List<Review>> getReviewsByTitle(@PathVariable String titleId) {
        List<Review> reviews = this.reviewService.getByTitle(titleId);
        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping(value = "/reviews/{titleId}/user")
    public ResponseEntity<Review> createUserReview(@RequestBody UserReview review, @PathVariable String titleId) {
        Review created = this.reviewService.create(review, titleId);
        return ResponseEntity.ok().body(created);
    }

    @PostMapping(value = "/reviews/{titleId}/premium")
    public ResponseEntity<Review> createPremiumReview(@RequestBody PremiumReview review, @PathVariable String titleId) {
        Review created = this.reviewService.create(review, titleId);
        return ResponseEntity.ok().body(created);
    }
}
