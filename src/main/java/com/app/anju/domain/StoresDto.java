package com.app.anju.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoresDto {

  private String id;

  private String name;

  private double longitude;

  private double latitude;

  private List<FoodDto> menus;

  private Integer point;

  private boolean visited;

  public StoresDto(Store store) {

    this.id = store.get_id();
    this.name = store.getName();
    this.longitude = store.getLongitude();
    this.latitude = store.getLatitude();
    this.point = store.getPoint();
    this.visited = store.isVisited();
  }

  public StoresDto(Store store, List<Food> foods) {

    this.id = store.get_id();
    this.name = store.getName();
    this.longitude = store.getLongitude();
    this.latitude = store.getLatitude();
    this.point = store.getPoint();
    this.visited = store.isVisited();
    this.menus = toFoodDto(foods);
  }

  public Store toStore() {

    return Store.builder()
        .name(this.name)
        .longitude(this.longitude)
        .latitude(this.latitude)
        .point(this.point)
        .visited(this.visited)
        .build();
  }

  public void toFoodDto(Food food) {

    if (this.menus == null) {
      this.menus = new ArrayList<>();
    }

    FoodDto foodDto = new FoodDto(food);
    this.menus.add(foodDto);
  }

  public List<FoodDto> toFoodDto(List<Food> foods) {

    return this.menus = foods.stream().map(FoodDto::new).collect(Collectors.toList());
  }
}
