package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.Specifications;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles.SearchTitleParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Component
public class TitleSpecsBuilder implements SpecBuilder<Title, SearchTitleParamsDto> {
    private final double TOP_RATED_VALUE = 4.5;
    private final Map<Integer, AbstractMap.SimpleEntry<Integer, Integer>> DECADES_DICT = this.initDecadesDict();

    @Override
    public Specification<Title> buildCriteriaSpecs(SearchTitleParamsDto params) {
        return Specification
                .where(withTitleName(params.getTitleName()))
                .and(withGenre(params.getGenre()))
                .and(withDecade(params.getDecade()))
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

    private Specification<Title> withDecade(Integer decade) {
        if (decade == null) return null;

        AbstractMap.SimpleEntry<Integer, Integer> decadePair = DECADES_DICT.getOrDefault(decade, null);

        return (root, query, cb) -> decadePair == null ? null :
                cb.between(root.get("startYear"), decadePair.getKey(), decadePair.getValue());
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

    private Map<Integer, AbstractMap.SimpleEntry<Integer, Integer>> initDecadesDict() {
        Map<Integer, AbstractMap.SimpleEntry<Integer, Integer>> decades = new HashMap<>();
        int startDecade = 1890;
        int endDecade = 2020;

        for (int i = startDecade; i <= endDecade; i = i+10) {
            int endYear = i+9;
            decades.put(i, new AbstractMap.SimpleEntry<>(i, endYear));
        }

        return decades;
    }
}
