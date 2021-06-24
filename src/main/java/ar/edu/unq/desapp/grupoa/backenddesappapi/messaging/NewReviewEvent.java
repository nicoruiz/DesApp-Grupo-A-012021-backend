package ar.edu.unq.desapp.grupoa.backenddesappapi.messaging;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.StartupConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Subscription;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.TitleRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.EmailSenderService;
import lombok.Data;

import java.io.Serializable;

@Data
public class NewReviewEvent implements Serializable, Event {
    private ReviewDto reviewDto;

    public NewReviewEvent(ReviewDto reviewDto) {
        this.reviewDto = reviewDto;
    }
    public NewReviewEvent() {}

    @Override
    public void handle() {
        TitleRepository titleRepository = StartupConfig.ApplicationContext.getBean(TitleRepository.class);
        String titleId = reviewDto.getTitleId();
        Title title = titleRepository.findById(titleId).get();

        for (Subscription sub : title.getSubscriptions()) {
            this.sendEmail(reviewDto, sub.getEmail());
        }
    }

    private void sendEmail(ReviewDto review, String destinationEmail) {
        EmailSenderService emailSenderService = StartupConfig.ApplicationContext.getBean(EmailSenderService.class);

        String subject = "New Reviews for Title: " + review.getTitlePrimaryTitle();
        String body = " - Resume: " + review.getResume() +
                "\n - Body: " + review.getBody() +
                "\n - Type: " + review.getReviewType() +
                "\n - Rating: " + review.getRating() +
                "\n - Platform: " + review.getPlatformUsername() +
                "\n - User: " + review.getUsername();

        emailSenderService.sendEmail(destinationEmail, subject, body);
    }
}
