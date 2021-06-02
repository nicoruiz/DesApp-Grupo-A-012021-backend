package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.CreateReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.SearchReviewParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.EntityNotFoundException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.PlatformRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.Specifications.ReviewSpecsBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
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
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private MapperUtil mapperUtil;
    @Autowired
    private ReviewSpecsBuilder reviewSpecsBuilder;

    public ReviewDto create(CreateReviewDto createReviewDto, String titleId) {
        Title title = titleRepository.findById(titleId)
                .orElseThrow(() -> new EntityNotFoundException("Title", titleId));

        Platform platform = platformRepository.findById(createReviewDto.getPlatformId())
                .orElseThrow(() -> new EntityNotFoundException("Platform", createReviewDto.getPlatformId()));

        Review newReview = mapperUtil.getMapper().map(createReviewDto, Review.class);
        newReview.setTitle(title);
        newReview.setPlatform(platform);
        reviewRepository.save(newReview);

        return mapperUtil.getMapper().map(newReview, ReviewDto.class);
    }

    public List<ReviewDto> getAll(Pageable pagingSort) {
        Page<Review> reviews = reviewRepository.findAll(pagingSort);

        return Arrays.asList(mapperUtil.getMapper().map(reviews.toList(), ReviewDto[].class));
    }

    public List<ReviewDto> getByCriteria(SearchReviewParamsDto params, Pageable pagingSort) {
        Specification<Review> specs = reviewSpecsBuilder.buildCriteriaSpecs(params);
        Page<Review> reviews = reviewRepository.findAll(specs, pagingSort);

        return Arrays.asList(mapperUtil.getMapper().map(reviews.toList(), ReviewDto[].class));
    }

    public ReviewDto like(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review", reviewId));
        review.like();
        return mapperUtil.getMapper().map(review, ReviewDto.class);
    }

    public ReviewDto dislike(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review", reviewId));
        review.dislike();
        return mapperUtil.getMapper().map(review, ReviewDto.class);
    }
}
