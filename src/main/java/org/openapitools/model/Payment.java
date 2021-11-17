package org.openapitools.model;

import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class Payment   {
  private int reservationId;
  private PaymentStatus fromCity;

}

