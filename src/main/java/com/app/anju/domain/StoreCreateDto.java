package com.app.anju.domain;

import com.app.anju.applications.DaumAddressResponse.Document;
import com.app.anju.domain.embed.Location;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreCreateDto {

  private String name;

  private Location location;

  private Integer point;

  private Boolean visited;

  private String description;

  private String address;

  public void addLocation(Document storeCoordinates) {

    List<Double> coordinates = new ArrayList<>();
    coordinates.add(storeCoordinates.getX());
    coordinates.add(storeCoordinates.getY());
    this.location = Location.builder().coordinates(coordinates).type("Point").build();
  }

  public Store toStore() {

    return Store.builder()
        .name(this.name)
        .location(this.location)
        .point(this.point)
        .visited(this.visited)
        .description(this.description)
        .address(this.address)
        .build();
  }
}
