package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;
}
