package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TitleDto implements Serializable {
    private String id;
    private String titleType;
    private String primaryTitle;
    private boolean isAdult;
    private int startYear;
    private Integer endYear;
    private String genres;
    private List<String> persons;
    private List<String> reviewResumes;
    private double averageRating;

    public TitleDto() {}
}
