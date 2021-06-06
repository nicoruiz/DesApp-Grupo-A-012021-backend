package ar.edu.unq.desapp.grupoa.backenddesappapi.model.report;

import javax.persistence.Entity;

@Entity
public class SpoilerReport extends Report {

    public SpoilerReport() {
        super();
    }
    
    public SpoilerReport(Long id, String comment) {
        super(id, comment);
    }
}
