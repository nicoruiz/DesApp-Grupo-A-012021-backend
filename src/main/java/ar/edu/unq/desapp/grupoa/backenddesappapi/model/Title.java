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
}
