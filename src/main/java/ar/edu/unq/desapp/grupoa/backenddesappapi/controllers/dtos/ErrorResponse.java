package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ErrorResponse implements Serializable {
    private final int status;
    private final String message;
}
