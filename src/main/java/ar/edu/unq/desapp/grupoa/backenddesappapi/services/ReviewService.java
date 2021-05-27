package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.CreatePremiumReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.CreateReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.EntityNotFoundException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.PlatformRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private MapperUtil mapperUtil;

    public ReviewDto create(Review newReview, String titleId) {
        Title title = titleRepository.findById(titleId)
                .orElseThrow(() -> new EntityNotFoundException(Title.class.getSimpleName(), titleId));

        Platform platform = platformRepository.findById(newReview.getPlatform().getId())
                .orElseThrow(() -> new EntityNotFoundException(Platform.class.getSimpleName(), newReview.getPlatform().getId()));

        newReview.setTitle(title);
        newReview.setPlatform(platform);
        reviewRepository.save(newReview);

        return mapperUtil.getMapper().map(newReview, ReviewDto.class);
    }

    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewRepository.findAll();

        return Arrays.asList(mapperUtil.getMapper().map(reviews, ReviewDto[].class));
    }

    public List<ReviewDto> getByTitle(String titleId) {
        Title title = titleRepository.findById(titleId)
                .orElseThrow(() -> new EntityNotFoundException(Title.class.getSimpleName(), titleId));
        List<Review> reviews = title.getReviews();

        return Arrays.asList(mapperUtil.getMapper().map(reviews, ReviewDto[].class));
    }
}
