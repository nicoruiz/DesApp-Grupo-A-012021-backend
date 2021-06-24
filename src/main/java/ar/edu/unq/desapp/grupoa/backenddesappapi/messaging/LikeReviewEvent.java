package ar.edu.unq.desapp.grupoa.backenddesappapi.messaging;

import ar.edu.unq.desapp.grupoa.backenddesappapi.security.ApiKeyValidatorAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class LikeReviewEvent implements Serializable, Event {
    @Override
    public void handle() {
        Logger logger = LoggerFactory.getLogger(LikeReviewEvent.class);
        logger.info("Like Review Event handled successfully.");
    }
}
