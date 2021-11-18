package org.openapitools.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "airlines")
@Entity
@Accessors(chain = true)
public class Airline {
    @Id
    String name;
    String api_key;
}