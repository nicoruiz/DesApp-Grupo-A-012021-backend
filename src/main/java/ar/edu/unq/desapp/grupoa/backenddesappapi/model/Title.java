package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Title {
    @Id
    public String id;
    public String titleType;
    public String primaryTitle;
    public String originalTitle;
    public boolean isAdult;
    public Date startYear;
    public Date endYear;
    public String genres;

    public Title() {}

    public Title(String id, String titleType, String primaryTitle, String originalTitle, boolean isAdult, Date startYear, Date endYear, String genres) {
        this.id = id;
        this.titleType = titleType;
        this. primaryTitle = primaryTitle;
        this. originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.genres = genres;
    }
}
