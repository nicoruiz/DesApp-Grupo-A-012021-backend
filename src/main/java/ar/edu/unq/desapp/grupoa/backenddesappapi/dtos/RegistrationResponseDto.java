package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationResponseDto implements Serializable {
    private Long id;
    private String platform;

    public RegistrationResponseDto() {}
}
