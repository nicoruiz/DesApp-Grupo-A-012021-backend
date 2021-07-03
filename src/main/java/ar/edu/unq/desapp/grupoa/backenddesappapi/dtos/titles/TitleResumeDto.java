package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.titles;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.TitleType;
import java.io.Serializable;
import lombok.Data;

@Data
public class TitleResumeDto implements Serializable {
    private String id;
    private TitleType titleType;
    private String primaryTitle;
    private int startYear;
    private double averageRating;
    private int amountReviews;
    
    public TitleResumeDto() {}
}
