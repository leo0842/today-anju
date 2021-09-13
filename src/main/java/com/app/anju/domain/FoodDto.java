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
public class FoodDto {

  private Long id;

  private String name;

  private Base base;

  private Method method;

  private String characteristic;

  private Integer price;

  private List<IngredientDto> ingredients;

  public FoodDto(Food food) {

    this.id = food.getId();
    this.name = food.getName();
    this.base = food.getBase();
    this.method = food.getMethod();
    this.characteristic = food.getCharacteristic();
    this.price = food.getPrice();
    this.ingredients = buildIngredient(food.getIngredientDetails());
  }

  private List<IngredientDto> buildIngredient(List<IngredientDetail> ingredientDetails) {

    return ingredientDetails.stream().map(IngredientDto::new).collect(Collectors.toList());
  }

  public void addIngredient(Ingredient ingredient) {

    if (this.ingredients == null) {

      this.ingredients = new ArrayList<>();
    }

    IngredientDto ingredientDto = new IngredientDto(ingredient);
    this.ingredients.add(ingredientDto);
  }
}
