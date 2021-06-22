package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class RabbitConnectionException extends RuntimeException {
    public RabbitConnectionException() {
        super("Error trying to connect to RabbitMQ service.");
    }
}
