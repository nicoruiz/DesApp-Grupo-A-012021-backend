package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("1")
public class Episode extends Title implements Serializable {
    @OneToOne(mappedBy = "episode")
    @JsonIgnoreProperties("episode")
    private EpisodeDetail episodeDetail;

    public Episode() {
        super();
    }
}
