package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
public class EpisodeDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "episode_id", referencedColumnName = "id")
    private Episode episode;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "serie_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"persons", "reviews"})
    private Title serie;
    private int seasonNumber;
    private int episodeNumber;
}
