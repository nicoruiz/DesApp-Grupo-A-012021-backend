package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.specifications;

import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.titles.SearchTitleParamsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import java.util.HashMap;
import java.util.Map;

@Component
public class TitleSpecsBuilder implements SpecBuilder<Title, SearchTitleParamsDto> {
    private final Map<Integer, Integer> DECADES_DICT = this.initDecadesDict();

    @Override
    public Specification<Title> buildCriteriaSpecs(SearchTitleParamsDto params) {
        return Specification
                .where(withTitleName(params.getTitleName()))
                .and(withGenre(params.getGenre()))
                .and(withDecade(params.getDecade()))
                .and(withRating(params.getRating()))
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

    private Specification<Title> withDecade(Integer decadeStart) {
        if (decadeStart == null) return null;

        Integer endYear = DECADES_DICT.getOrDefault(decadeStart, null);

        return (root, query, cb) -> endYear == null ? null :
                cb.between(root.get("startYear"), decadeStart, endYear);
    }

    private Specification<Title> withRating(Double rating) {
        return (root, query, cb) -> rating == null ? null :
                cb.ge(root.join("reviews", JoinType.LEFT).get("rating"), rating);
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

    private Map<Integer, Integer> initDecadesDict() {
        Map<Integer, Integer> decades = new HashMap<>();
        int startDecade = 1890;
        int endDecade = 2020;

        for (int i = startDecade; i <= endDecade; i = i+10) {
            int endYear = i+9;
            decades.put(i, endYear);
        }
        return decades;
    }
}
