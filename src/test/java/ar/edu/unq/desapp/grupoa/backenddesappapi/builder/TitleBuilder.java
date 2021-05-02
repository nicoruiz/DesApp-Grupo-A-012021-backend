package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Person;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.TitleType;

import java.util.ArrayList;
import java.util.List;

public class TitleBuilder {

    public static TitleBuilder aTitle() {
        return new TitleBuilder();
    }
    private String id = "tt0000001";
    private TitleType titleType = TitleType.MOVIE;
    private String primaryTitle = "No title";
    private int isAdult = 0;
    private int startYear = 1900;
    private Integer endYear;
    private String genres = "Comedy";
    private List<Person> persons = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public Title build() {
        return new Title(id, titleType, primaryTitle, isAdult, startYear, endYear, genres, persons, reviews);
    }
    public TitleBuilder withTitleType(final TitleType aTitleType) {
        titleType = aTitleType;
        return this;
    }
    public TitleBuilder withPrimaryTitle(final String aPrimaryTitle) {
        primaryTitle = aPrimaryTitle;
        return this;
    }
    public TitleBuilder withIsAdult(final int anIsAdult) {
        isAdult = anIsAdult;
        return this;
    }
    public TitleBuilder withStartYear(final int aStartYear) {
        startYear = aStartYear;
        return this;
    }
    public TitleBuilder withEndYear(final int anEndYear) {
        endYear = anEndYear;
        return this;
    }
    public TitleBuilder withGenres(final String someGenres) {
        genres = someGenres;
        return this;
    }
    public TitleBuilder withPersons(final List<Person> somePersons) {
        persons = somePersons;
        return this;
    }
    public TitleBuilder withReviews(final List<Review> someReviews) {
        reviews = someReviews;
        return this;
    }
}
