package ar.edu.unq.desapp.grupoa.backenddesappapi.model.report;

import javax.persistence.Entity;

@Entity
public class NoSenseReport extends Report {

    public NoSenseReport() {
        super();
    }
    
    public NoSenseReport(Long id, String comment) {
        super(id, comment);
    }
}
