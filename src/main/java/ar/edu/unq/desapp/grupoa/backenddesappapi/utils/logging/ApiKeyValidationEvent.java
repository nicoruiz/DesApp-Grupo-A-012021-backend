package ar.edu.unq.desapp.grupoa.backenddesappapi.utils.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiKeyValidationEvent implements LoggingEvent{
    private final String apiKey;

    @Override
    public void log() {
        Logger logger = LogManager.getLogger(ApiKeyValidationEvent.class);
        logger.info("[API-KEY] {} validated successfully.", this.apiKey);
    }

    public ApiKeyValidationEvent(String apiKey) {
        this.apiKey = apiKey;
    }
}
