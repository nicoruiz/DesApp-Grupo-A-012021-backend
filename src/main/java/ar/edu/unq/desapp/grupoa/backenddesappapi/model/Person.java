package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

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
}
