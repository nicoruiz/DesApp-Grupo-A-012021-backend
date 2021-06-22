package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponseDto implements Serializable {
    private final String platform;
    private final String token;

    public JwtResponseDto(String platform, String token) {
        this.platform = platform;
        this.token = token;
    }
}
