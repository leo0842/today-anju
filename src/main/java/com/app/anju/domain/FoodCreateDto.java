package com.app.anju.domain;

import java.util.List;
import lombok.Data;

@Data
public class FoodCreateDto {

  private String name;

  private Base base;

  private Method method;

  private String characteristic;

  private Integer price;

  private List<Ingredient> ingredients;


}
