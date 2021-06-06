package ar.edu.unq.desapp.grupoa.backenddesappapi.model.report;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    @JsonIgnoreProperties("reports")
    private Review review;

    public Report() {
        
    }
    
    public Report(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
