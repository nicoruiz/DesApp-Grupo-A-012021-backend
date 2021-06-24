package ar.edu.unq.desapp.grupoa.backenddesappapi.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class DislikeReviewEvent implements Serializable, Event {
    @Override
    public void handle() {
        Logger logger = LoggerFactory.getLogger(DislikeReviewEvent.class);
        logger.info("Dislike Review Event handled successfully.");
    }
}
