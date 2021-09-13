package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

  private Long id;

  private String name;

  public IngredientDto(Ingredient ingredient) {

    this.id = ingredient.getId();
    this.name = ingredient.getName();
  }

  public IngredientDto(IngredientDetail ingredientDetail) {

    this.id = ingredientDetail.getId();
    this.name = ingredientDetail.getIngredient().getName();
  }
}
