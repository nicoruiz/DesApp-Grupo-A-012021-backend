package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class NonExistentPlatformException extends RuntimeException {
    public NonExistentPlatformException(String username) {
        super("Specified platform "+ username +" does not exist.");
    }
}
