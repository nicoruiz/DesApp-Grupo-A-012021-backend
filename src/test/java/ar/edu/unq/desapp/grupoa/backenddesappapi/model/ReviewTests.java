package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.UserReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.PlatformType;
import org.junit.jupiter.api.Assertions;
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

        Assertions.assertEquals(anUserReview.getTitle(), aTitle);
    }
}
