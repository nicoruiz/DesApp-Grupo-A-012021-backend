package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ErrorResponseDto implements Serializable {
    private final int status;
    private final String message;
}
