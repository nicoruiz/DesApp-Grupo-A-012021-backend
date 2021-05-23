package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationResponseDto implements Serializable {
    private Long id;
    private String platform;

    public RegistrationResponseDto(Long id, String platform) {
        this.id = id;
        this.platform = platform;
    }
}
