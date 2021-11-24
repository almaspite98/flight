package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDto {
    @Schema(description = "Error message", example = "name must not be null")
    private String message;
    @Schema(description = "Time of the error's occurrence")
    private Instant timestamp;
}
