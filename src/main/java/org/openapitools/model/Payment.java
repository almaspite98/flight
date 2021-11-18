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
@Table(name = "payments")
@Entity
@Accessors(chain = true)
public class Payment {
  @Id
  private Integer reservationId;
  private String status;
  private Instant timestamp;
}

