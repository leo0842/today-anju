package com.app.anju;

import com.app.anju.domain.Base;
import com.app.anju.domain.Food;
import com.app.anju.domain.Ingredient;
import com.app.anju.domain.Method;
import com.app.anju.domain.Store;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/mock")
  public List<Store> mock() {

    List<Store> stores = new ArrayList<>();
    Food porkBelly = new Food("삼겹살", Base.SOY_SOURCE, Method.FRY, Ingredient.PORK, "맛있는 삼겹살", 17000);
    Food porkShoulder = new Food("목살", Base.SOY_SOURCE, Method.FRY, Ingredient.PORK, "맛있는 목살", 17000);
    Store 땅코 = new Store("1", "땅코", 127.033080, 37.560662, new ArrayList<>(), 5, true, "굳굳", "왕십리");
    땅코.addMenu(porkBelly);
    땅코.addMenu(porkShoulder);
    stores.add(땅코);

    Food 레몬_탕수육 = new Food("레몬탕수육", Base.RED_PEPPER_SOURCE, Method.FRY, Ingredient.PORK, "레탕", 25000);
    Food 소고기볶음 = new Food("소고기볶음", Base.SOY_SOURCE, Method.STIR, Ingredient.BEEF, "소고기", 13000);
    Store 구포 = new Store("2", "구포", 127.051750, 37.580805, new ArrayList<>(), 5, true, "구포는구포", "청량리");
    구포.addMenu(레몬_탕수육);
    구포.addMenu(소고기볶음);
    stores.add(구포);

    Food chicken = new Food("파닭", Base.SOY_SOURCE, Method.SOUP, Ingredient.CHICKEN, "파닭은 네네", 20000);
    Store 네네치킨 = new Store("3", "네네치킨", 127.041535, 37.563127, new ArrayList<>(), null, false, "비둘기", "왕십리");
    네네치킨.addMenu(chicken);
    stores.add(네네치킨);

    return stores;
  }

}
