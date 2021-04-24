package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import javax.persistence.*;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String primaryName;
    @OneToOne(mappedBy = "actor")
    public Cast cast;
    public Integer birthYear;
    public Integer deathYear;
    public String professions;
    /*@OneToMany
    @JoinColumn(name = "title_id")
    public Collection<Title> knownForTitles = new ArrayList<Title>();
    */

    public Actor() {}
}
