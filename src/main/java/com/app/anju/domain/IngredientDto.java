package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

  private Long id;

  private String ingredientName;

  public IngredientDto(Ingredient ingredient) {

    this.id = ingredient.getId();
    this.ingredientName = ingredient.getIngredientName();
  }

}
