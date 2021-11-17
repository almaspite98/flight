package org.openapitools.model;

import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResult {
    int reservation_id;
    PaymentStatus status;
}
