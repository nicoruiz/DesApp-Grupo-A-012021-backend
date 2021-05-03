package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.PremiumReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.UserReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.UserReview;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
        Title aTitle = TitleBuilder.aTitle().build();
        UserReview anUserReview = UserReviewBuilder.anUserReview().build();
        PremiumReview aPremiumReview = PremiumReviewBuilder.aPremiumReview().build();

        anUserReview.setTitle(aTitle);
        aPremiumReview.setTitle(aTitle);
        when(repositoryMock.findAll()).thenReturn(Arrays.asList(anUserReview, aPremiumReview));

        assertThat(repositoryMock.findAll().size()).isEqualTo(2);
    }

    @Test
    public void testSearchAllReviewsFromTitle_Get2Reviews() {
        TitleRepository repositoryMock = mock(TitleRepository.class);
        String titleId = "tt0000002";
        Title aTitle = TitleBuilder.aTitle()
                        .withId(titleId)
                        .build();
        Optional<Title> optionalTitle = Optional.of(aTitle);

        UserReview anUserReview = UserReviewBuilder.anUserReview().build();
        PremiumReview aPremiumReview = PremiumReviewBuilder.aPremiumReview().build();
        List<Review> reviewsToAdd = Arrays.asList(anUserReview, aPremiumReview);
        aTitle.setReviews(reviewsToAdd);

        when(repositoryMock.findById(titleId)).thenReturn(optionalTitle);
        List<Review> reviews = repositoryMock.findById(titleId).get().getReviews();

        assertThat(reviews.size()).isEqualTo(2);
        assertThat(anUserReview).isIn(reviews);
        assertThat(aPremiumReview).isIn(reviews);
    }
}
