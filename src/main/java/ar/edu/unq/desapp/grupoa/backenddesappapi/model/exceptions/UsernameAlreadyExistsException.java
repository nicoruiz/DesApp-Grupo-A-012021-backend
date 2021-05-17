package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Specified username " + username +" is already in use.");
    }
}
