package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Title {
    @Id
    public String id;
    public String titleType;
    public String primaryTitle;
    public String originalTitle;
    public boolean isAdult;
    public Integer startYear;
    public Integer endYear;
    public String genres;
    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("title")
    public Collection<Cast> cast = new ArrayList<Cast>();

    public Title() {}

    public Title(String id, String titleType, String primaryTitle, String originalTitle, boolean isAdult, Integer startYear, Integer endYear, String genres) {
        this.id = id;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.genres = genres;
    }
}
