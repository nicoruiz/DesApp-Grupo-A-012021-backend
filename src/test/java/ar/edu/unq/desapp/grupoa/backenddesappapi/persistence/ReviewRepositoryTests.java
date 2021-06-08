package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.ReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewRepositoryTests {

    @Test
    public void testSearchAllReviews_Get2Reviews() {
        ReviewRepository repositoryMock = mock(ReviewRepository.class);
        Pageable pagingMock = mock(PageRequest.class);

        Title aTitle = TitleBuilder.aTitle().build();
        Review aReview = ReviewBuilder.aReview().build();
        Review aCriticReview = ReviewBuilder.aReview().build();

        aReview.setTitle(aTitle);
        aCriticReview.setTitle(aTitle);
        when(repositoryMock.findAll(pagingMock))
                .thenReturn(new PageImpl<>(Arrays.asList(aReview, aCriticReview)));

        Page<Review> reviewPage = repositoryMock.findAll(pagingMock);

        assertThat(reviewPage.toList().size()).isEqualTo(2);
    }

    @Test
    public void testSearchAllReviewsFromTitle_Get2Reviews() {
        TitleRepository repositoryMock = mock(TitleRepository.class);
        String titleId = "tt0000002";
        Title aTitle = TitleBuilder.aTitle()
                        .withId(titleId)
                        .build();
        Optional<Title> optionalTitle = Optional.of(aTitle);

        Review aReview = ReviewBuilder.aReview().build();
        Review aCriticReview = ReviewBuilder.aReview().build();
        List<Review> reviewsToAdd = Arrays.asList(aReview, aCriticReview);
        aTitle.setReviews(reviewsToAdd);

        when(repositoryMock.findById(titleId)).thenReturn(optionalTitle);
        List<Review> reviews = repositoryMock.findById(titleId).get().getReviews();

        assertThat(reviews.size()).isEqualTo(2);
        assertThat(aReview).isIn(reviews);
        assertThat(aCriticReview).isIn(reviews);
    }
}
