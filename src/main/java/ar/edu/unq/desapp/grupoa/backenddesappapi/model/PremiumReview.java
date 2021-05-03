package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.PlatformType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("2")
public class PremiumReview extends Review implements Serializable {

    public PremiumReview() {}

    public PremiumReview(long id, String resume, String body, int rating, Date createdOn, PlatformType platformType, int platformUserId, String language, Title title) {
        super(id, resume, body, rating, createdOn, platformType, platformUserId, language, title);
    }
}
