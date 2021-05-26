package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.PlatformType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="is_premium", discriminatorType = DiscriminatorType.INTEGER)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resume;
    private String body;
    private int rating;
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
    private int likes;
    private int dislikes;

    public Review() {}

    public Review(long id, String resume, String body, int rating, Date createdOn, Platform platform, int platformUserId, String language, Title title) {
        this.id = id;
        this.resume = resume;
        this.body = body;
        this.rating = rating;
        this.createdOn = createdOn;
        this.platform = platform;
        this.platformUserId = platformUserId;
        this.language = language;
        this.title = title;
    }
    
    public void like() {
        likes++;
    }
    
    public void dislike() {
        dislikes++;
    }
}