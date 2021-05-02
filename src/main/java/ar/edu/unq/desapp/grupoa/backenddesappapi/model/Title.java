package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.TitleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="is_episode", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class Title implements Serializable {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TitleType titleType;
    private String primaryTitle;
    private int isAdult;
    private int startYear;
    private Integer endYear;
    private String genres;
    @ManyToMany
    @JoinTable(
            name = "title_person",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @JsonIgnoreProperties("titles")
    private List<Person> persons;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    @JsonIgnoreProperties("title")
    private List<Review> reviews;

    public Title() {}

    public Title(String id, TitleType titleType, String primaryTitle, int isAdult, int startYear, Integer endYear, String genres, List<Person> persons, List<Review> reviews) {
        this.id = id;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.genres = genres;
        this.persons = persons;
        this.reviews = reviews;
    }
}
