package com.app.anju.domain;

import java.util.ArrayList;
import java.util.List;
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

  private double longitude;

  private double latitude;

  private List<Food> menus;

  private Integer point;

  private boolean visited;

  private String description;

  private String address;

  public void addMenu(Food food) {

    this.menus.add(food);
  }
}
