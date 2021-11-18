package org.openapitools.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Accessors(chain = true)
public class UserWithPreferences {
    @Id
    private String email;
    private String password;
    private String departure;
    private Integer transferTime;
    private String airline;
    private String token;
}
