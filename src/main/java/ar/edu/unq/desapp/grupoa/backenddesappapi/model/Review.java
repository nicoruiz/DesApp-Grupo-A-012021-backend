package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.PlatformType;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private int rating;
    private Date date;
    @Enumerated(EnumType.STRING)
    private PlatformType platformType;
    private int platformUserId;
    private String language;
}
