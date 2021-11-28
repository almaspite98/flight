package org.openapitools.model;

import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class User   {
  private String email;
  private String password;
}

