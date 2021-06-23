package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlatformDetailsDto implements Serializable {
    private String platform;
    private String apiKey;
    private double credits;
    private int processedRequests;
    private double pricePerRequest;

    public PlatformDetailsDto() {}
}
