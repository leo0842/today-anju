package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

  private String foodName;

  private Base base;

  private Method method;

  private Ingredient ingredient;

  private String characteristic;

  private Integer price;
}
