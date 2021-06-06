package ar.edu.unq.desapp.grupoa.backenddesappapi.model.report;

import javax.persistence.Entity;

@Entity
public class OffensiveReport extends Report {

    public OffensiveReport() {
        super();
    }
    
    public OffensiveReport(Long id, String comment) {
        super(id, comment);
    }
}
