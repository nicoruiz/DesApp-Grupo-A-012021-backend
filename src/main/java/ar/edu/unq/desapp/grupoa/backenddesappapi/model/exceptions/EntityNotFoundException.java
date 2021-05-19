package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, String id) {
        super(entity +" with id "+ id +" not found.");
    }

    public EntityNotFoundException(String entity, Long id) {
        super(entity +" with id "+ id +" not found.");
    }
}
