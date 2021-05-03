package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TitleRepository titleRepository;

    public Review create(Review review, String titleId) {
        // Check if title exists
        Title title = titleRepository.findById(titleId).get();
        review.setTitle(title);
        return this.reviewRepository.save(review);
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public List<Review> getByTitle(String titleId) {
        Title title = titleRepository.findById(titleId).get();
        return title.getReviews();
    }
}
