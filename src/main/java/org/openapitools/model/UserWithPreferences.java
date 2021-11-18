package org.openapitools.model;

import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPreferences {
    private String email;
    private String password;
    private String departure;
    private Integer transferTime;
    private String airline;
    private String token;
}
