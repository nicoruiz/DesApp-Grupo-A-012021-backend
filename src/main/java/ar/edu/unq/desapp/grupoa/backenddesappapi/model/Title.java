package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.TitleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "title_id")
    private List<Episode> episodes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "title_id")
    private List<UserReview> userReviews;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "title_id")
    private List<PremiumReview> premiumReviews;
}
