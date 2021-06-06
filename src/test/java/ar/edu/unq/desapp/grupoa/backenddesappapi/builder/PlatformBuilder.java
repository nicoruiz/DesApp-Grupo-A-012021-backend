package ar.edu.unq.desapp.grupoa.backenddesappapi.builder;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;

import java.util.UUID;

public class PlatformBuilder {
    public static PlatformBuilder aPlatform() {
        return new PlatformBuilder();
    }

    private long id = 1;
    private String username = "netflix";
    private String password = "password";
    private String apiKey = UUID.randomUUID().toString();
    private double credits = 0;
    private int processedRequests = 0;
    private double pricePerRequest = 0;

    public Platform build() {
        return new Platform(id, username, password, apiKey, credits, processedRequests, pricePerRequest);
    }

    public PlatformBuilder withId(final long anId) {
        id = anId;
        return this;
    }
    public PlatformBuilder withUsername(final String anUsername) {
        username = anUsername;
        return this;
    }
    public PlatformBuilder withPassword(final String aPassword) {
        password = aPassword;
        return this;
    }
    public PlatformBuilder withApiKey(final String anApiKey) {
        apiKey = anApiKey;
        return this;
    }
    public PlatformBuilder withCredits(final double someCredits) {
        credits = someCredits;
        return this;
    }
    public PlatformBuilder withProcessedRequests(final int someRequests) {
        processedRequests = someRequests;
        return this;
    }
    public PlatformBuilder withPricePerRequest(final double somePricePerRequest) {
        pricePerRequest = somePricePerRequest;
        return this;
    }
}
