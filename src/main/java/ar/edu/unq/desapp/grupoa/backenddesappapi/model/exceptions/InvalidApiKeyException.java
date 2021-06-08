package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class InvalidApiKeyException extends RuntimeException {
    public InvalidApiKeyException() {
        super("API KEY is missing or invalid.");
    }
}
