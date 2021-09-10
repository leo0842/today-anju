package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Ingredient {

  CHICKEN("닭고기"),
  PORK("돼지고기"),
  BEEF("소고기");

  private String ingredientName;
}
