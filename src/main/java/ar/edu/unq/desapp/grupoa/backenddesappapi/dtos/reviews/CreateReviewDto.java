package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.reviews;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateReviewDto implements Serializable {
    @NotEmpty(message = "Resume may not be empty")
    private String resume;
    @NotEmpty(message = "Body may not be empty")
    private String body;
    @NotNull(message = "ReviewType may not be null")
    private ReviewType reviewType;
    @NotNull(message = "Rating may not be null")
    private double rating;
    @NotNull(message = "PlatformId may not be null")
    private Long platformId;
    @NotNull(message = "PlatformUserId may not be null")
    private int platformUserId;
    @NotEmpty(message = "Language may not be empty")
    private String language;
    private boolean hasSpoiler;
    private String username;
    private String localization;
}
