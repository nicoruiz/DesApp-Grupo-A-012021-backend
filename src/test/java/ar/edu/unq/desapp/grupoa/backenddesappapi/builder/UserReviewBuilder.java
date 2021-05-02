package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.UserReview;

public class UserReviewBuilder extends ReviewBuilder<UserReviewBuilder> {
    public static UserReviewBuilder anUserReview() {
        return new UserReviewBuilder();
    }
    @Override
    public UserReview build() {
        return new UserReview(id, resume, body, rating, createdOn, platformType, platformUserId, language, title, hasSpoiler, username, localization);
    }
    private boolean hasSpoiler = false;
    private String username = "anUsername";
    private String localization = "AR";

    public UserReviewBuilder withSpoiler(final boolean aSpoiler) {
        hasSpoiler = aSpoiler;
        return this;
    }
    public UserReviewBuilder withUsername(final String anUsername) {
        username = anUsername;
        return this;
    }
    public UserReviewBuilder withLocalization(final String aLocalization) {
        localization = aLocalization;
        return this;
    }
}
