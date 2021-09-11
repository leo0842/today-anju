package com.app.anju.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

  private Long id;

  private String foodName;

  private Base base;

  private Method method;

  private String characteristic;

  private Integer price;

  private List<IngredientDto> ingredients;

  public FoodDto(Food food) {
    this.id = food.getId();
    foodName = food.getFoodName();
    base = food.getBase();
    method = food.getMethod();
    characteristic = food.getCharacteristic();
    price = food.getPrice();
  }

  public void addIngredient(Ingredient ingredient) {

    if (this.ingredients == null) {

      this.ingredients = new ArrayList<>();
    }

    IngredientDto ingredientDto = new IngredientDto(ingredient);
    this.ingredients.add(ingredientDto);
  }
}
