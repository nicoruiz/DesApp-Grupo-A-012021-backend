package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateReviewDto implements Serializable {
    private String resume;
    private String body;
    private ReviewType reviewType;
    private double rating;
    private Long platformId;
    private int platformUserId;
    private String language;
    private boolean hasSpoiler;
    private String username;
    private String localization;
}
