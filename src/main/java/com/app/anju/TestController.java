package com.app.anju;

import com.app.anju.domain.Base;
import com.app.anju.domain.Food;
import com.app.anju.domain.Ingredient;
import com.app.anju.domain.Method;
import com.app.anju.domain.Store;
import com.app.anju.domain.StoresDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/mock")
  public List<StoresDto> mock() {

    List<StoresDto> stores = new ArrayList<>();
    Food porkBelly = new Food(101L, "삼겹살", Base.SOY_SOURCE, Method.FRY, "맛있는 삼겹살", 17000, null, null);
    Food porkShoulder = new Food(102L, "목살", Base.SOY_SOURCE, Method.FRY, "맛있는 목살", 17000, null, null);
    Ingredient pork = new Ingredient(11L, "돼지삼겹살", null);
    Ingredient pork2 = new Ingredient(12L, "돼지목살", null);
    Ingredient 참숯 = new Ingredient(13L, "참숯", null);
    Store 땅코 = new Store("1", "땅코", 127.033080, 37.560662, 5, true, "굳굳", "왕십리");
    StoresDto 땅코Dto = new StoresDto(땅코);
    땅코Dto.toFoodDto(porkBelly);
    땅코Dto.toFoodDto(porkShoulder);
    땅코Dto.getMenus().get(0).addIngredient(pork);
    땅코Dto.getMenus().get(0).addIngredient(참숯);
    땅코Dto.getMenus().get(1).addIngredient(pork2);
    땅코Dto.getMenus().get(1).addIngredient(참숯);
    stores.add(땅코Dto);

//    Food 레몬_탕수육 = new Food(103L, "레몬탕수육", Base.RED_PEPPER_SOURCE, Method.FRY, "레탕", 25000, null, null);
//    Food 소고기볶음 = new Food(104L, "소고기볶음", Base.SOY_SOURCE, Method.STIR, "소고기", 13000, null, null);
//    Store 구포 = new Store("2", "구포", 127.051750, 37.580805, 5, true, "구포는구포", "청량리");
//    stores.add(구포);
//
//    Food chicken = new Food(105L, "파닭", Base.SOY_SOURCE, Method.SOUP, "파닭은 네네", 20000, null, null);
//    Store 네네치킨 = new Store("3", "네네치킨", 127.041535, 37.563127, null, false, "비둘기", "왕십리");
//    stores.add(네네치킨);

    return stores;
  }

}
