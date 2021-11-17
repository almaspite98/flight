package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Preferences
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-11-15T21:12:04.614312100+01:00[Europe/Prague]")
public class Preferences   {
  @JsonProperty("departure")
  private String departure;

  @JsonProperty("transfer_time")
  private Integer transferTime;

  @JsonProperty("airline")
  private String airline;

  public Preferences departure(String departure) {
    this.departure = departure;
    return this;
  }

  /**
   * Get departure
   * @return departure
  */
  @ApiModelProperty(value = "")


  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public Preferences transferTime(Integer transferTime) {
    this.transferTime = transferTime;
    return this;
  }

  /**
   * Get transferTime
   * @return transferTime
  */
  @ApiModelProperty(value = "")


  public Integer getTransferTime() {
    return transferTime;
  }

  public void setTransferTime(Integer transferTime) {
    this.transferTime = transferTime;
  }

  public Preferences airline(String airline) {
    this.airline = airline;
    return this;
  }

  /**
   * Get airline
   * @return airline
  */
  @ApiModelProperty(value = "")


  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Preferences preferences = (Preferences) o;
    return Objects.equals(this.departure, preferences.departure) &&
        Objects.equals(this.transferTime, preferences.transferTime) &&
        Objects.equals(this.airline, preferences.airline);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departure, transferTime, airline);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Preferences {\n");
    
    sb.append("    departure: ").append(toIndentedString(departure)).append("\n");
    sb.append("    transferTime: ").append(toIndentedString(transferTime)).append("\n");
    sb.append("    airline: ").append(toIndentedString(airline)).append("\n");
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

