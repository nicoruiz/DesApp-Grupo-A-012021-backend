package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.PremiumReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.UserReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.PlatformType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewTests {

    @Test
    public void testCreateUserReview_UserReviewHasTitleAssigned() {
        Title aTitle = TitleBuilder.aTitle()
                        .withPrimaryTitle("Mortal Kombat")
                        .withGenres("Action")
                        .withStartYear(2021)
                        .build();
        UserReview anUserReview = UserReviewBuilder.anUserReview()
                                    .withResume("Review MK 2021")
                                    .withBody("Podría haber estado mejor. Scorpion aparece recien al final.")
                                    .withPlatformType(PlatformType.AMAZON_PRIME)
                                    .withSpoiler(true)
                                    .withRating(3)
                                    .build();

        anUserReview.setTitle(aTitle);

        assertThat(anUserReview.getTitle()).isEqualTo(aTitle);
    }
    
    @Test
    public void testCreatePremiumReview_PremiumReviewHasTitleAssigned() {
        Title aTitle = TitleBuilder.aTitle()
                        .withPrimaryTitle("Back to the Future")
                        .withGenres("Science fiction")
                        .withStartYear(1985)
                        .build();
        PremiumReview anPremiumReview = PremiumReviewBuilder.aPremiumReview()
                                          .withResume("Review Back to the Future 1985")
                                          .withBody("Genial actuacion de Michael Fox.")
                                          .withPlatformType(PlatformType.NETFLIX)
                                          .withRating(4)
                                          .build();
        
        anPremiumReview.setTitle(aTitle);
        
        assertThat(anPremiumReview.getTitle()).isEqualTo(aTitle);
    }
    
    @Test
    public void testValuateUserReview_UserReviewHasLike() {
        UserReview anUserReview = UserReviewBuilder.anUserReview()
                                    .withResume("Review MK 2021")
                                    .withBody("Podría haber estado mejor. Scorpion aparece recien al final.")
                                    .withPlatformType(PlatformType.AMAZON_PRIME)
                                    .withSpoiler(true)
                                    .withRating(3)
                                    .build();

        int likesBefore = anUserReview.getLikes();
        anUserReview.like();
        
        assertThat(anUserReview.getLikes()).isEqualTo(likesBefore + 1);
    }
    
    @Test
    public void testValuateUserReview_UserReviewHasDislike() {
        UserReview anUserReview = UserReviewBuilder.anUserReview()
                                    .withResume("Review MK 2021")
                                    .withBody("Podría haber estado mejor. Scorpion aparece recien al final.")
                                    .withPlatformType(PlatformType.AMAZON_PRIME)
                                    .withSpoiler(true)
                                    .withRating(3)
                                    .build();

        int dislikesBefore = anUserReview.getDislikes();
        anUserReview.dislike();
        
        assertThat(anUserReview.getDislikes()).isEqualTo(dislikesBefore + 1);
    }
}
