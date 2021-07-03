package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Subscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    @JsonIgnoreProperties("subscriptions")
    private Title title;
    private String email;

    public Subscription(Title title, String email) {
        this.title = title;
        this.email = email;
    }

    public Subscription() {}
}
