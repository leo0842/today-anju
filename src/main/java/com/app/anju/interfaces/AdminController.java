package com.app.anju.interfaces;

import com.app.anju.applications.FoodService;
import com.app.anju.applications.IngredientService;
import com.app.anju.applications.MethodService;
import com.app.anju.applications.SauceService;
import com.app.anju.applications.StoreService;
import com.app.anju.domain.Food;
import com.app.anju.domain.FoodCreateDto;
import com.app.anju.domain.Ingredient;
import com.app.anju.domain.MethodDto;
import com.app.anju.domain.SauceDto;
import com.app.anju.domain.StoreKeyValueDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

  private final FoodService foodService;
  private final StoreService storeService;
  private final MethodService methodService;
  private final SauceService sauceService;
  private final IngredientService ingredientService;

  @GetMapping("/food")
  public String addMenu(@ModelAttribute FoodCreateDto foodCreateDto, Model model) {

    List<Food> foodList = foodService.getAllFood();
    List<SauceDto> sauceList = sauceService.getValues();
    List<MethodDto> methodList = methodService.getValues();
    List<StoreKeyValueDto> storeList = storeService.getStoreValue();
    List<Ingredient> ingredientList = ingredientService.getIngredients();

    model.addAttribute("foodCreateDto", foodCreateDto);
    model.addAttribute("foodList", foodList);
    model.addAttribute("sauceList", sauceList);
    model.addAttribute("methodList", methodList);
    model.addAttribute("storeList", storeList);
    model.addAttribute("ingredientList", ingredientList);

    return "admin/admin-menu";
  }

  @GetMapping("/store")
  public String addStore() {

    return "admin/admin-store";
  }
}
