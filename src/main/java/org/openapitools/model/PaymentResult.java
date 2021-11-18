package org.openapitools.model;

import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResult {
    Integer reservation_id;
    String status;
}
