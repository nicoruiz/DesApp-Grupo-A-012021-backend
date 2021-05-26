package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class CreateReviewDto implements Serializable {
    private String resume;
    private String body;
    private int rating;
    private Long platformId;
    private int platformUserId;
    private String language;
}
