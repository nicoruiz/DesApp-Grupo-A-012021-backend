package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.Specifications;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.SearchReviewParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ReviewSpecsBuilder implements SpecBuilder<Review, SearchReviewParamsDto> {

    @Override
    public Specification<Review> buildCriteriaSpecs(SearchReviewParamsDto params) {
        return Specification
                .where(withTitleId(params.getTitleId()))
                .and(withPlatformName(params.getPlatform()))
                .and(withSpoiler(params.getSpoiler()))
                .and(withType(params.getType()))
                .and(withLanguage(params.getLanguage()))
                .and(withLocalization(params.getLocalization()));
    }

    private Specification<Review> withTitleId(String titleId) {
        return (root, query, cb) ->
                cb.equal(root.get("title").get("id"), titleId);
    }

    private Specification<Review> withPlatformName(String platformName) {
        return (root, query, cb) -> platformName == null ? null :
                cb.equal(root.get("platform").get("username"), platformName);
    }

    private Specification<Review> withSpoiler(Boolean spoiler) {
        return (root, query, cb) -> spoiler == null ? null :
                cb.equal(root.get("hasSpoiler"), spoiler);
    }

    private Specification<Review> withType(String type) {
        if (type != null) {
            if (type.equalsIgnoreCase("review")) {
                return (root, query, cb) -> cb.equal(root.get("reviewType"), ReviewType.REVIEW);
            }
            else if (type.equalsIgnoreCase("critic")) {
                return (root, query, cb) -> cb.equal(root.get("reviewType"), ReviewType.CRITIC);
            }
        }
        return (root, query, cb) -> null;
    }

    private Specification<Review> withLanguage(String language) {
        return (root, query, cb) -> language == null ? null :
                cb.equal(root.get("language"), language);
    }

    private Specification<Review> withLocalization(String localization) {
        return (root, query, cb) -> localization == null ? null :
                cb.equal(root.get("localization"), localization);
    }
}
