package com.app.anju.domain;

import java.util.List;
import lombok.Data;

@Data
public class StoreDto {

  private String name;

  private double longitude;

  private double latitude;

  private List<Food> menu;

  private Integer price;

  private Integer point;

  private boolean visited;
}
