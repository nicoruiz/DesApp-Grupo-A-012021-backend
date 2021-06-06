package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.BadWordsReport;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.NoSenseReport;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.OffensiveReport;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.Report;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.report.SpoilerReport;

public class ReportBuilder {
    
    public static ReportBuilder aReport() {
        return new ReportBuilder();
    }
    public Report buildSpoilerReport() {
        return new SpoilerReport(id, comment);
    }
    public Report buildBadWordsReport() {
        return new BadWordsReport(id, comment);
    }
    public Report buildOffensiveReport() {
        return new OffensiveReport(id, comment);
    }
    public Report buildNoSenseReport() {
        return new NoSenseReport(id, comment);
    }
    
    private long id = 1;
    private String comment;
    
    public ReportBuilder withComment(final String aComment) {
        comment = aComment;
        return this;
    }
    
}

