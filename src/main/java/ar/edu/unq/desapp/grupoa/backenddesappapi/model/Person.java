package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
public class Person implements Serializable {

    @Id
    private String id;
    private String primaryName;
    @ManyToMany(mappedBy = "persons")
    @JsonIgnoreProperties("persons")
    private List<Title> titles;

    public Person(String id, String primaryName, List<Title> titles) {
        this.id = id;
        this.primaryName = primaryName;
        this.titles = titles;
    }

    public Person() {}
}
