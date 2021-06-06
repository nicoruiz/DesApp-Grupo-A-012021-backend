package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.Specifications;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles.SearchTitleParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;

@Component
public class TitleSpecsBuilder implements SpecBuilder<Title, SearchTitleParamsDto> {
    private final double TOP_RATED_VALUE = 4.5;

    @Override
    public Specification<Title> buildCriteriaSpecs(SearchTitleParamsDto params) {
        return Specification
                .where(withTitleName(params.getTitleName()))
                .and(withGenre(params.getGenre()))
                .and(withYear(params.getYear()))
                .and(withTopRated(params.getTopRated()))
                .and(withPersonName(params.getPersonName()))
                .and(distinct());
    }

    private Specification<Title> withTitleName(String titleName) {
        return (root, query, cb) -> titleName == null ? null :
                cb.like(root.get("primaryTitle"), "%" + titleName + "%");
    }

    private Specification<Title> withGenre(String genre) {
        return (root, query, cb) -> genre == null ? null :
                cb.like(root.get("genres"), "%" + genre + "%");
    }

    private Specification<Title> withYear(Integer year) {
        return (root, query, cb) -> year == null ? null :
                cb.equal(root.get("startYear"), year);
    }

    private Specification<Title> withTopRated(Boolean topRated) {
        return (root, query, cb) -> topRated == null ? null :
                topRated ?
                    cb.greaterThanOrEqualTo(root.join("reviews", JoinType.LEFT).get("rating"), TOP_RATED_VALUE) :
                    cb.lessThan(root.join("reviews", JoinType.LEFT).get("rating"), TOP_RATED_VALUE);
    }

    private Specification<Title> withPersonName(String personName) {
        return (root, query, cb) -> personName == null ? null :
                cb.like(root.join("persons", JoinType.LEFT).get("primaryName"), "%" + personName + "%");
    }

    private Specification<Title> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }
}
