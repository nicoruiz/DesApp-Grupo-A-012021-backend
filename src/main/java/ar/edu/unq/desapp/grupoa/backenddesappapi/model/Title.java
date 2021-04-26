package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Title implements Serializable {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TitleType titleType;
    private String primaryTitle;
    private int startYear;
    private int endYear;
    private String genres;
    @ManyToMany(mappedBy = "titles")
    private Set<Person> persons;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "title_id")
    private Set<Episode> episodes;
}
