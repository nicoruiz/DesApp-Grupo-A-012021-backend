package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.PremiumReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.UserReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.PremiumReview;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.UserReview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

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

        Assertions.assertEquals(2, repositoryMock.findAll().size());
    }
}
