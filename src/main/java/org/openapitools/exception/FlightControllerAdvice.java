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


@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class FlightControllerAdvice {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> handleNonUserCannotModifyException(final AuthenticationException exception) {
        return createResponseEntity(exception, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<ErrorDto> createResponseEntity(Exception exception, HttpStatus httpStatus) {
        log.error("Mapped exception: {} > {}", exception.getClass().getSimpleName(), exception.getMessage());
        var error = ErrorDto.builder()
                .message(exception.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(error, httpStatus);
    }
}
