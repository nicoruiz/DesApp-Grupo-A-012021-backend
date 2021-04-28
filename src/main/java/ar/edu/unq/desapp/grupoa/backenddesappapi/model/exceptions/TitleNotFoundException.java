package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(String id) {
        super("Could not find Title " + id);
    }
}
