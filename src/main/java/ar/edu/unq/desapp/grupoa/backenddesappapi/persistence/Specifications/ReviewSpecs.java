package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.Specifications;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ReviewSpecs {

    public Specification<Review> buildCriteriaSpecs(String titleId, String platform, Boolean spoiler, String type) {
        return Specification
                .where(withTitleId(titleId))
                .and(withPlatformName(platform))
                .and(withSpoiler(spoiler))
                .and(withType(type));
    }

    private Specification<Review> withTitleId(String titleId) {
        return (root, query, cb) -> titleId == null ? null :
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
}
