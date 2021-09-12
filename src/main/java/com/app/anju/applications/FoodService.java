package com.app.anju.applications;

import com.app.anju.domain.Food;
import com.app.anju.domain.FoodCreateDto;
import com.app.anju.domain.FoodRepository;
import com.app.anju.domain.Ingredient;
import com.app.anju.domain.IngredientDetail;
import com.app.anju.domain.IngredientDetailRepository;
import com.app.anju.domain.IngredientRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {

  private final FoodRepository foodRepository;
  private final IngredientRepository ingredientRepository;
  private final IngredientDetailRepository ingredientDetailRepository;

  public void addFood(FoodCreateDto foodCreateDto) {

    Food food = Food.builder()
        .name(foodCreateDto.getName())
        .base(foodCreateDto.getBase())
        .method(foodCreateDto.getMethod())
        .characteristic(foodCreateDto.getCharacteristic())
        .price(foodCreateDto.getPrice())
        .build();

    foodRepository.save(food);

    List<Ingredient> ingredients = foodCreateDto.getIngredients().stream()
        .map(ingredient -> ingredientRepository.save(
            Ingredient.builder().name(ingredient.getName()
            ).build())).collect(Collectors.toList());

    ingredients
        .forEach(ingredient -> ingredientDetailRepository.save(
          IngredientDetail.builder().ingredient(ingredient).food(food).build()));
  }
}
