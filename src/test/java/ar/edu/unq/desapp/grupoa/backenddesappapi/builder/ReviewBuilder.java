package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;

import java.util.Date;

public class ReviewBuilder {
    public static ReviewBuilder aReview() {
        return new ReviewBuilder();
    }
    public Review build() {
        return new Review(id, resume, body, reviewType, rating, createdOn, platform, platformUserId, language, title, hasSpoiler, username, localization);
    }

    private long id = 1;
    private String resume = "a resume";
    private String body = "a body";
    private ReviewType reviewType = ReviewType.CRITIC;
    private int rating = 5;
    private Date createdOn = new Date();
    private Platform platform = PlatformBuilder.aPlatform().build();
    private int platformUserId = 1;
    private String language = "ES";
    private Title title;
    private boolean hasSpoiler;
    private String username;
    private String localization;

    public ReviewBuilder withResume(final String aResume) {
        resume = aResume;
        return this;
    }
    public ReviewBuilder withBody(final String aBody) {
        body = aBody;
        return this;
    }
    public ReviewBuilder withReviewType(final ReviewType aType) {
        reviewType = aType;
        return this;
    }
    public ReviewBuilder withRating(int aRating){
        rating = aRating;
        return this;
    }
    public ReviewBuilder withCreatedOn(final Date aCreatedOn) {
        createdOn = aCreatedOn;
        return this;
    }
    public ReviewBuilder withPlatform(final Platform aPlatform) {
        platform = aPlatform;
        return this;
    }
    public ReviewBuilder withPlatformUserId(final int aPlatformUserId) {
        platformUserId = aPlatformUserId;
        return this;
    }
    public ReviewBuilder withLanguage(final String aLanguage) {
        language = aLanguage;
        return this;
    }
    public ReviewBuilder withTitle(final Title aTitle) {
        title = aTitle;
        return this;
    }
    public ReviewBuilder withSpoiler(final boolean aSpoiler) {
        hasSpoiler = aSpoiler;
        return this;
    }
    public ReviewBuilder withUsername(final String anUsername) {
        username = anUsername;
        return this;
    }
    public ReviewBuilder withLocalization(final String aLocalization) {
        localization = aLocalization;
        return this;
    }
}
