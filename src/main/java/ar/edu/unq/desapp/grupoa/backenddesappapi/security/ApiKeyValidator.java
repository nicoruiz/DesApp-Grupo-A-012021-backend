package ar.edu.unq.desapp.grupoa.backenddesappapi.security;


import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.InvalidApiKeyException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.PlatformRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.logging.ApiKeyValidationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyValidator {
    @Autowired
    private PlatformRepository platformRepository;

    public String validateApiKey(String apiKey) {
        Platform platform = platformRepository.findByApiKey(apiKey);
        if(platform == null)
            throw new InvalidApiKeyException();

        logEvent(platform.getApiKey());
        processRequest(platform);

        return platform.getUsername();
    }

    private void processRequest(Platform platform) {
        platform.processRequest();
        platformRepository.save(platform);
    }

    private void logEvent(String apiKey) {
        ApiKeyValidationEvent loggingEvent = new ApiKeyValidationEvent(apiKey);
        loggingEvent.log();
    }
}
