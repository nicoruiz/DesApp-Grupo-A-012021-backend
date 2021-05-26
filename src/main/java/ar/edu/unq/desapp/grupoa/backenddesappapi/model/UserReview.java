package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.PlatformType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("0")
public class UserReview extends Review implements Serializable {
    private boolean hasSpoiler;
    private String username;
    private String localization;

    public UserReview() {}

    public UserReview(long id, String resume, String body, int rating, Date createdOn, Platform platform, int platformUserId, String language, Title title, boolean hasSpoiler, String username, String localization) {
        super(id, resume, body, rating, createdOn, platform, platformUserId, language, title);
        this.hasSpoiler = hasSpoiler;
        this.username = username;
        this.localization = localization;
    }
}
