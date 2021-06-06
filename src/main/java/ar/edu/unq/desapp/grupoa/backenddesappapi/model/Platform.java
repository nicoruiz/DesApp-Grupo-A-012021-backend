package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Platform implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String apiKey;
    private double credits = 10000.00;
    private int requestsNumber;
    private double pricePerRequest = 1.25;

    public Platform(long id, String username, String password, String apiKey) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.apiKey = apiKey;
    }

    public Platform() {}

    public void processRequest() {
        this.requestsNumber++;
        this.credits = this.credits - this.pricePerRequest;
    }
}
