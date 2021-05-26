package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.enums.PlatformType;

import java.util.Date;

public abstract class ReviewBuilder<T extends ReviewBuilder<T>> {
    protected long id = 1;
    protected String resume = "a resume";
    protected String body = "a body";
    protected int rating = 5;
    protected Date createdOn = new Date();
    protected Platform platform = PlatformBuilder.aPlatform().build();
    protected int platformUserId = 1;
    protected String language = "ES";
    protected Title title;

    public T withResume(final String aResume) {
        resume = aResume;
        return self();
    }
    public T withBody(final String aBody) {
        body = aBody;
        return self();
    }
    public T withRating(int aRating){
        rating = aRating;
        return self();
    }
    public T withCreatedOn(final Date aCreatedOn) {
        createdOn = aCreatedOn;
        return self();
    }
    public T withPlatform(final Platform aPlatform) {
        platform = aPlatform;
        return self();
    }
    public T withPlatformUserId(final int aPlatformUserId) {
        platformUserId = aPlatformUserId;
        return self();
    }
    public T withLanguage(final String aLanguage) {
        language = aLanguage;
        return self();
    }
    public T withTitle(final Title aTitle) {
        title = aTitle;
        return self();
    }

    public abstract Review build();

    @SuppressWarnings("unchecked")
    final T self() {
        return (T) this;
    }

}
