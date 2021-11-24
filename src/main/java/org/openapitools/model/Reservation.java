package org.openapitools.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;


@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
@Entity
@Accessors(chain = true)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Integer groupId;
    private String flightId;
    private String email;
    private Instant timestamp;
    private String status;
}
