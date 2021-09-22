package com.app.anju.applications;

import com.app.anju.domain.Food;
import com.app.anju.domain.FoodCreateDto;
import com.app.anju.domain.FoodRepository;
import com.app.anju.domain.IngredientRepository;
import com.app.anju.domain.MethodRepository;
import com.app.anju.domain.SauceRepository;
import com.app.anju.domain.StoreMenu;
import com.app.anju.domain.StoreMenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {

  private final FoodRepository foodRepository;
  private final StoreMenuRepository storeMenuRepository;
  private final IngredientRepository ingredientRepository;
  private final MethodRepository methodRepository;
  private final SauceRepository sauceRepository;

  public void addFood(FoodCreateDto foodCreateDto) {

    StoreMenu storeMenu = StoreMenu.builder()
        .storeId(foodCreateDto.getStore())
        .name(foodCreateDto.getName())
        .food(foodRepository.getById(foodCreateDto.getFood()))
        .method(methodRepository.getById(foodCreateDto.getMethod()))
        .price(foodCreateDto.getPrice())
        .characteristic(foodCreateDto.getCharacteristic())
        .build();

    foodCreateDto.getSauces().stream().map(sauceRepository::getById).forEach(storeMenu::addSauce);
    foodCreateDto.getIngredients().stream().map(ingredientRepository::getById).forEach(storeMenu::addIngredient);

    storeMenuRepository.save(storeMenu);
  }

  public List<Food> getAllFood() {

    return foodRepository.findAll();
  }
}
