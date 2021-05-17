package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponseDto implements Serializable {
    private final String token;

    public JwtResponseDto(String token) {
        this.token = token;
    }
}
