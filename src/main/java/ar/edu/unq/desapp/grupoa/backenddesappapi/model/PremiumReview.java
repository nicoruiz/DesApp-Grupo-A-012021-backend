package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("2")
public class PremiumReview extends Review implements Serializable {
}
