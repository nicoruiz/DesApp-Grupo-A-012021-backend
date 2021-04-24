package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name="title_id", referencedColumnName = "id", nullable = false)
    public Title title;
    public Integer ordering;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    @JsonIgnoreProperties("cast")
    public Actor actor;
    public String category;
    public String job;
    public String characters;

    public Cast() {}
}
