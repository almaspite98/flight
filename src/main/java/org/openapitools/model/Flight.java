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
@Table(name = "flights")
@Entity
@Accessors(chain = true)
public class Flight   {
  @Id
  private String flightId;
  private Instant departureTime;
  private Instant arrivalTime;
  private String airline;
  private String fromCity;
  private String toCity;
  private int numberOfSeats;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Flight {\n");
    
    sb.append("    id: ").append(toIndentedString(flightId)).append("\n");
    sb.append("    departureTime: ").append(toIndentedString(departureTime)).append("\n");
    sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n");
    sb.append("    fromCity: ").append(toIndentedString(fromCity)).append("\n");
    sb.append("    toCity: ").append(toIndentedString(toCity)).append("\n");
    sb.append("    numberOfSeats: ").append(toIndentedString(numberOfSeats)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

