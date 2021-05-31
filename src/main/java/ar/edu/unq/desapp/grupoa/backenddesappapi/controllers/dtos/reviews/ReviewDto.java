package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class ReviewDto implements Serializable {
    private Long id;
    private String resume;
    private String body;
    private String reviewType;
    private int rating;
    private Date createdOn;
    private String platformUsername;
    private int platformUserId;
    private String language;
    private String titleId;
    private String titlePrimaryTitle;
    private String titleType;
    private int likes;
    private int dislikes;
    private boolean hasSpoiler;
    private String username;
    private String localization;

    public ReviewDto() {}
}
