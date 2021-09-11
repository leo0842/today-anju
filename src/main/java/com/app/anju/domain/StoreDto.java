package com.app.anju.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class StoreDto {

  private String id;

  private String name;

  private double longitude;

  private double latitude;

  private List<FoodDto> menu;

  private Integer point;

  private boolean visited;

  public StoreDto(Store store) {
    this.id = store.get_id();
    this.name = store.getName();
    this.longitude = store.getLongitude();
    this.latitude = store.getLatitude();
    this.point = store.getPoint();
    this.visited = store.isVisited();
  }

  public void addFood(Food food) {
    if (this.menu == null) {
      this.menu = new ArrayList<>();
    }

    FoodDto foodDto = new FoodDto(food);
    this.menu.add(foodDto);
  }
}
