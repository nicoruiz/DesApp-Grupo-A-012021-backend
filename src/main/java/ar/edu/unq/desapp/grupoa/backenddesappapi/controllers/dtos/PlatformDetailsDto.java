package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlatformDetailsDto implements Serializable {
    private String platform;
    private String apiKey;
    private double credits;
    private int requestsNumber;
    private double pricePerRequest;

    public PlatformDetailsDto() {}
}
