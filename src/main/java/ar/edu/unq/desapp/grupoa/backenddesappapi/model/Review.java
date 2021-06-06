package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.ReviewType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resume;
    private String body;
    @Enumerated(EnumType.STRING)
    private ReviewType reviewType;
    private double rating;
    @Temporal(TemporalType.DATE)
    private Date createdOn = new Date();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "platform_id", referencedColumnName = "id")
    private Platform platform;
    private int platformUserId;
    private String language;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    @JsonIgnoreProperties("reviews")
    private Title title;
    private boolean hasSpoiler;
    private String username;
    private String localization;
    private int likes;
    private int dislikes;

    public Review() {}

    public Review(long id, String resume, String body, ReviewType reviewType, double rating, Date createdOn, Platform platform, int platformUserId, String language, Title title, boolean hasSpoiler, String username, String localization) {
        this.id = id;
        this.resume = resume;
        this.body = body;
        this.reviewType = reviewType;
        this.rating = rating;
        this.createdOn = createdOn;
        this.platform = platform;
        this.platformUserId = platformUserId;
        this.language = language;
        this.title = title;
        this.hasSpoiler = hasSpoiler;
        this.username = username;
        this.localization = localization;
    }
    
    public void like() {
        likes++;
    }
    
    public void dislike() {
        dislikes++;
    }
}