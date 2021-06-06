package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.PersonBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.ReviewBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.TitleBuilder;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.TitleType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TitleTests {

    @Test
    public void testAssignTwoPersonsToTitle_TitleHasPersonsAssigned() {
        Person tomHanks = PersonBuilder.aPerson()
                        .withId("1")
                        .withName("Tom Hanks")
                        .build();
        Person mattDamon = PersonBuilder.aPerson()
                        .withId("2")
                        .withName("Matt Damon")
                        .build();
        Title savingPrivateRyan = TitleBuilder.aTitle()
                        .withId("tt0120815")
                        .withPrimaryTitle("Saving Private Ryan")
                        .withTitleType(TitleType.MOVIE)
                        .withStartYear(1998)
                        .withGenres("War")
                        .withPersons(Arrays.asList(tomHanks, mattDamon))
                        .build();

        List<String> personNames = savingPrivateRyan.getPersonNames();

        assertThat(personNames.size()).isEqualTo(2);
        assertThat(personNames.containsAll(Arrays.asList("Tom Hanks", "Matt Damon"))).isTrue();
    }

    @Test
    public void testAssignTwoReviewsWithResumeToTitle_TitleHasReviewsAssigned() {
        Review review1 = ReviewBuilder.aReview()
                .withResume("Muy buena")
                .withBody("Gran fotografía y escenografía")
                .withReviewType(ReviewType.CRITIC)
                .build();
        Review review2 = ReviewBuilder.aReview()
                .withResume("Mal doblaje")
                .withBody("El doblaje latino no era muy bueno, lo demas ok")
                .withReviewType(ReviewType.REVIEW)
                .build();
        Title savingPrivateRyan = TitleBuilder.aTitle()
                .withId("tt0120815")
                .withPrimaryTitle("Saving Private Ryan")
                .withTitleType(TitleType.MOVIE)
                .withStartYear(1998)
                .withGenres("War")
                .withReviews(Arrays.asList(review1, review2))
                .build();

        List<String> reviewResumes = savingPrivateRyan.getReviewResumes();

        assertThat(reviewResumes.size()).isEqualTo(2);
        assertThat(reviewResumes.containsAll(Arrays.asList("Muy buena", "Mal doblaje"))).isTrue();
    }

    @Test
    public void testAssignTwoReviewsWithRatingToTitle_TitleHasAnAverageRatingOf4point5() {
        Review review1 = ReviewBuilder.aReview()
                .withResume("Muy buena")
                .withBody("Gran fotografía y escenografía")
                .withReviewType(ReviewType.CRITIC)
                .withRating(4.7)
                .build();
        Review review2 = ReviewBuilder.aReview()
                .withResume("Mal doblaje")
                .withBody("El doblaje latino no era muy bueno, lo demas ok")
                .withReviewType(ReviewType.REVIEW)
                .withRating(4.3)
                .build();
        Title savingPrivateRyan = TitleBuilder.aTitle()
                .withId("tt0120815")
                .withPrimaryTitle("Saving Private Ryan")
                .withTitleType(TitleType.MOVIE)
                .withStartYear(1998)
                .withGenres("War")
                .withReviews(Arrays.asList(review1, review2))
                .build();

        double avgRating = savingPrivateRyan.getAverageRating();

        assertThat(savingPrivateRyan.getReviews().size()).isEqualTo(2);
        assertThat(avgRating).isEqualTo(4.5);
    }
}
