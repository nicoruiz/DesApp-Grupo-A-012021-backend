package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.PlatformBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.ReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewTests {

    @Test
    public void testCreateReview_ReviewHasTitleAssigned() {
        Title aTitle = TitleBuilder.aTitle()
                    .withPrimaryTitle("Mortal Kombat")
                    .withGenres("Action")
                    .withStartYear(2021)
                    .build();
        Review aReview = ReviewBuilder.aReview()
                    .withResume("Review MK 2021")
                    .withBody("Podría haber estado mejor. Scorpion aparece recien al final.")
                    .withReviewType(ReviewType.REVIEW)
                    .withPlatform(PlatformBuilder.aPlatform().build())
                    .withSpoiler(true)
                    .withRating(3)
                    .withUsername("test")
                    .withLocalization("AR")
                    .build();

        aReview.setTitle(aTitle);

        assertThat(aReview.getTitle()).isEqualTo(aTitle);
    }
    
    @Test
    public void testCreateCriticReview_CriticReviewHasTitleAssigned() {
        Title aTitle = TitleBuilder.aTitle()
                    .withPrimaryTitle("Back to the Future")
                    .withGenres("Science fiction")
                    .withStartYear(1985)
                    .build();
        Review aCriticReview = ReviewBuilder.aReview()
                    .withResume("Review Back to the Future 1985")
                    .withBody("Genial actuacion de Michael Fox.")
                    .withReviewType(ReviewType.CRITIC)
                    .withPlatform(PlatformBuilder.aPlatform().build())
                    .withRating(4)
                    .build();

        aCriticReview.setTitle(aTitle);
        
        assertThat(aCriticReview.getTitle()).isEqualTo(aTitle);
    }
    
    @Test
    public void testValuateReview_ReviewHasLike() {
        Review aReview = ReviewBuilder.aReview()
                    .withResume("Review MK 2021")
                    .withBody("Podría haber estado mejor. Scorpion aparece recien al final.")
                    .withReviewType(ReviewType.REVIEW)
                    .withPlatform(PlatformBuilder.aPlatform().build())
                    .withSpoiler(true)
                    .withRating(3)
                    .build();

        int likesBefore = aReview.getLikes();
        aReview.like();
        
        assertThat(aReview.getLikes()).isEqualTo(likesBefore + 1);
    }
    
    @Test
    public void testValuateUserReview_UserReviewHasDislike() {
        Review anUserReview = ReviewBuilder.aReview()
                    .withResume("Review MK 2021")
                    .withBody("Podría haber estado mejor. Scorpion aparece recien al final.")
                    .withReviewType(ReviewType.REVIEW)
                    .withPlatform(PlatformBuilder.aPlatform().build())
                    .withSpoiler(true)
                    .withRating(3)
                    .build();

        int dislikesBefore = anUserReview.getDislikes();
        anUserReview.dislike();
        
        assertThat(anUserReview.getDislikes()).isEqualTo(dislikesBefore + 1);
    }
}
