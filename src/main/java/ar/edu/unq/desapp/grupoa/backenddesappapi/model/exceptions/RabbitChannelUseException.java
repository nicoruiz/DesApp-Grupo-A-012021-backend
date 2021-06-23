package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

public class RabbitChannelUseException extends RuntimeException {
    public RabbitChannelUseException() {
        super("Error trying to use Rabbit channel.");
    }
}
