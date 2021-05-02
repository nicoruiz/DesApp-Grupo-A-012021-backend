package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.PremiumReview;

public class PremiumReviewBuilder extends ReviewBuilder<PremiumReviewBuilder> {
    public static PremiumReviewBuilder aPremiumReview() {
        return new PremiumReviewBuilder();
    }
    @Override
    public PremiumReview build() {
        return new PremiumReview(id, resume, body, rating, createdOn, platformType, platformUserId, language, title);
    }
}
