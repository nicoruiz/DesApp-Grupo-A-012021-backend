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

    public Platform build() {
        return new Platform(id, username, password, apiKey);
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
}
