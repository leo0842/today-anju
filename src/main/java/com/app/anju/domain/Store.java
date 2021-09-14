package com.app.anju.domain;

import com.app.anju.domain.embed.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Store {

  @Id
  private String _id;

  private String name;

  private Location location;

  private Integer point;

  private boolean visited;

  private String description;

  private String address;

}
