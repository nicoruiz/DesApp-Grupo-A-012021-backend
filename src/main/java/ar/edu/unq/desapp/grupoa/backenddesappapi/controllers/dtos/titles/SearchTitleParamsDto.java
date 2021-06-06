package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.titles;

import lombok.Data;

@Data
public class SearchTitleParamsDto {
    private String titleName;
    private String genre;
    private Integer decade;
    private Boolean topRated;
    private String personName;

    public SearchTitleParamsDto(String titleName, String genre, Integer decade, Boolean topRated, String personName) {
        this.titleName = titleName;
        this.genre = genre;
        this.decade = decade;
        this.topRated = topRated;
        this.personName = personName;
    }
}
