package org.openapitools.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.openapitools.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.security.sasl.AuthenticationException;
import java.time.Instant;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class FlightControllerAdvice {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> handleAuthenticationException(final AuthenticationException exception) {
        return createResponseEntity(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalArgumentException.class, })
    public ResponseEntity<ErrorDto> handleRunTimeException(final RuntimeException exception) {
        return createResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchElementException.class,})
    public ResponseEntity<ErrorDto> handleNoSuchElementException(final NoSuchElementException exception) {
        return createResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity<ErrorDto> defaultHandler(final Exception exception) {
        log.error("Unhandled exception: {} > {}", exception.getClass().getSimpleName(), exception.getMessage());
        return createResponseEntity(exception, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDto> createResponseEntity(Exception exception, HttpStatus httpStatus) {
        log.error("Mapped exception: {} > {}", exception.getClass().getSimpleName(), exception.getMessage());
        var error = ErrorDto.builder()
                .message(exception.getLocalizedMessage())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(error, httpStatus);
    }
}
