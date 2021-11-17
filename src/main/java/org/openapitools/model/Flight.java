package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

/**
 * Flight
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-11-15T21:12:04.614312100+01:00[Europe/Prague]")
@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "flight")
@Table(name = "flights")
@Entity
@Accessors(chain = true)
public class Flight   {
  @JsonProperty("id")
  @Id
  private int id;

  @JsonProperty("departure_time")
  @org.springframework.format.annotation.DateTimeFormat(iso = DATE_TIME)
  private Instant departureTime;

  @JsonProperty("arrival_time")
  @org.springframework.format.annotation.DateTimeFormat(iso = DATE_TIME)
  private Instant arrivalTime;

  @JsonProperty("from_city")
  private String fromCity;

  @JsonProperty("to_city")
  private String toCity;

  @JsonProperty("number_of_seats")
  private int numberOfSeats;

//  public Flight id(int id) {
//    this.id = id;
//    return this;
//  }

//  /**
//   * Get id
//   * @return id
//  */
//  @ApiModelProperty(value = "")


//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public Flight departureTime(Instant departureTime) {
//    this.departureTime = departureTime;
//    return this;
//  }
//
//  /**
//   * Get departureTime
//   * @return departureTime
//  */
//  @ApiModelProperty(value = "")

//  @Valid
//
//  public Instant getDepartureTime() {
//    return departureTime;
//  }
//
//  public void setDepartureTime(Instant departureTime) {
//    this.departureTime = departureTime;
//  }
//
//  public Flight arrivalTime(Instant arrivalTime) {
//    this.arrivalTime = arrivalTime;
//    return this;
//  }
//
//  /**
//   * Get arrivalTime
//   * @return arrivalTime
//  */
//  @ApiModelProperty(value = "")
//
//  @Valid
//
//  public Instant getArrivalTime() {
//    return arrivalTime;
//  }
//
//  public void setArrivalTime(Instant arrivalTime) {
//    this.arrivalTime = arrivalTime;
//  }
//
//  public Flight fromCity(String fromCity) {
//    this.fromCity = fromCity;
//    return this;
//  }
//
//  /**
//   * Get fromCity
//   * @return fromCity
//  */
//  @ApiModelProperty(value = "")
//
//
//  public String getFromCity() {
//    return fromCity;
//  }
//
//  public void setFromCity(String fromCity) {
//    this.fromCity = fromCity;
//  }
//
//  public Flight toCity(String toCity) {
//    this.toCity = toCity;
//    return this;
//  }
//
//  /**
//   * Get toCity
//   * @return toCity
//  */
//  @ApiModelProperty(value = "")
//
//
//  public String getToCity() {
//    return toCity;
//  }
//
//  public void setToCity(String toCity) {
//    this.toCity = toCity;
//  }
//
//  public Flight numberOfSeats(int numberOfSeats) {
//    this.numberOfSeats = numberOfSeats;
//    return this;
//  }
//
//  /**
//   * Get numberOfSeats
//   * @return numberOfSeats
//  */
//  @ApiModelProperty(value = "")
//
//
//  public int getNumberOfSeats() {
//    return numberOfSeats;
//  }
//
//  public void setNumberOfSeats(int numberOfSeats) {
//    this.numberOfSeats = numberOfSeats;
//  }
//

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    Flight flight = (Flight) o;
//    return Objects.equals(this.id, flight.id) &&
//        Objects.equals(this.departureTime, flight.departureTime) &&
//        Objects.equals(this.arrivalTime, flight.arrivalTime) &&
//        Objects.equals(this.fromCity, flight.fromCity) &&
//        Objects.equals(this.toCity, flight.toCity) &&
//        Objects.equals(this.numberOfSeats, flight.numberOfSeats);
//  }

//  @Override
//  public int hashCode() {
//    return Objects.hash(id, departureTime, arrivalTime, fromCity, toCity, numberOfSeats);
//  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Flight {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    departureTime: ").append(toIndentedString(departureTime)).append("\n");
    sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n");
    sb.append("    fromCity: ").append(toIndentedString(fromCity)).append("\n");
    sb.append("    toCity: ").append(toIndentedString(toCity)).append("\n");
    sb.append("    numberOfSeats: ").append(toIndentedString(numberOfSeats)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

