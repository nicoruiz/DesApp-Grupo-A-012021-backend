package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                exception
        );
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(
            UsernameAlreadyExistsException exception
    ) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                exception
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException (
            BadCredentialsException exception
    ) {
        return buildErrorResponse(
                HttpStatus.FORBIDDEN,
                exception
        );
    }

    @ExceptionHandler(NonExistentPlatformException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNonExistentPlatformException(
            NonExistentPlatformException exception
    ) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception
        );
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleInvalidApiKeyException(
            InvalidApiKeyException exception
    ) {
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                exception
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            RuntimeException exception
    ){
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus httpStatus,
            Exception exception
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                exception.getMessage()
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
