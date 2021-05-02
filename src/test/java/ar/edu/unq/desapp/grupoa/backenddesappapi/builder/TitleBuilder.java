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
    public TitleBuilder withPrimaryTitle(String aPrimaryTitle) {
        primaryTitle = aPrimaryTitle;
        return this;
    }
    public TitleBuilder withIsAdult(int anIsAdult) {
        isAdult = anIsAdult;
        return this;
    }
    public TitleBuilder withStartYear(int aStartYear) {
        startYear = aStartYear;
        return this;
    }
    public TitleBuilder withEndYear(int anEndYear) {
        endYear = anEndYear;
        return this;
    }
    public TitleBuilder withGenres(String someGenres) {
        genres = someGenres;
        return this;
    }
    public TitleBuilder withPersons(List<Person> somePersons) {
        persons = somePersons;
        return this;
    }
    public TitleBuilder withReviews(List<Review> someReviews) {
        reviews = someReviews;
        return this;
    }
}
