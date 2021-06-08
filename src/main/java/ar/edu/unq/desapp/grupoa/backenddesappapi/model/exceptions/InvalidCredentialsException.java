package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Username and password cannot be empty or null.");
    }
}
