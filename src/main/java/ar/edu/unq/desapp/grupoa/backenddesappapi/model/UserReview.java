package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UserReview extends Review implements Serializable {
    private boolean hasSpoiler;
    private String username;
    private String localization;
}
