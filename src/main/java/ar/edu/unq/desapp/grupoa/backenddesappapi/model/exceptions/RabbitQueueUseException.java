package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class RabbitQueueUseException extends RuntimeException {
    public RabbitQueueUseException(String exchangeName) {
        super("Error trying to use exchange: " + exchangeName);
    }
}
