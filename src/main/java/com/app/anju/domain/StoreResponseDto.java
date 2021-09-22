package com.app.anju.domain;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

  private String id;

  private String name;

  private double longitude;

  private double latitude;

  private List<FoodDto> menus;

  private Integer point;

  private String address;

  private boolean visited;

  public StoreResponseDto(Store store, List<StoreMenu> menus) {

    this.id = store.get_id();
    this.name = store.getName();
    this.longitude = store.getLocation().getCoordinates().get(0);
    this.latitude = store.getLocation().getCoordinates().get(1);
    this.point = store.getPoint();
    this.visited = store.isVisited();
    this.address = store.getAddress();
    this.menus = toFoodDto(menus);
  }

  public List<FoodDto> toFoodDto(List<StoreMenu> menus) {

    return this.menus = menus.stream().map(FoodDto::new).collect(Collectors.toList());
  }

}
