package ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions;

import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.ErrorResponseDto;
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
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                exception
        );
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseDto> handleUsernameAlreadyExistsException(
            UsernameAlreadyExistsException exception
    ) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                exception
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException (
            BadCredentialsException exception
    ) {
        return buildErrorResponse(
                HttpStatus.FORBIDDEN,
                exception
        );
    }

    @ExceptionHandler(NonExistentPlatformException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleNonExistentPlatformException(
            NonExistentPlatformException exception
    ) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception
        );
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDto> handleInvalidApiKeyException(
            InvalidApiKeyException exception
    ) {
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                exception
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponseDto> handleInvalidCredentialsException(
            InvalidCredentialsException exception
    ) {
        return buildErrorResponse(
                HttpStatus.FORBIDDEN,
                exception
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleAllUncaughtException(
            RuntimeException exception
    ){
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception
        );
    }

    private ResponseEntity<ErrorResponseDto> buildErrorResponse(
            HttpStatus httpStatus,
            Exception exception
    ) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                httpStatus.value(),
                exception.getMessage()
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
