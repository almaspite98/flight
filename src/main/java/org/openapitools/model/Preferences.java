package org.openapitools.model;

import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class Preferences   {
  //from city
  private String departure;
  private Integer transferTime;
  private String airline;
}


