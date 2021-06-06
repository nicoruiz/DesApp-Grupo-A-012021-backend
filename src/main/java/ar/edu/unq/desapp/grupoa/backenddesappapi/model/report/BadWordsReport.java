package ar.edu.unq.desapp.grupoa.backenddesappapi.model.report;

import javax.persistence.Entity;

@Entity
public class BadWordsReport extends Report {

    public BadWordsReport() {
        super();
    }
    
    public BadWordsReport(Long id, String comment) {
        super(id, comment);
    }
}
