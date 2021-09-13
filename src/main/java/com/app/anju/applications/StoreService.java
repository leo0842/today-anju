package com.app.anju.applications;

import com.app.anju.domain.Food;
import com.app.anju.domain.FoodDetail;
import com.app.anju.domain.FoodDetailRepository;
import com.app.anju.domain.Store;
import com.app.anju.domain.StoresDto;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final MongoTemplate mongoTemplate;
  private final FoodDetailRepository foodDetailRepository;

  public void insertStore(StoresDto storesDto) {

    Store store = mongoTemplate.insert(storesDto.toStore());
  }

  public List<StoresDto> getStores() {

    List<Store> stores = mongoTemplate.findAll(Store.class);
    List<FoodDetail> foodDetails = foodDetailRepository.findByStoreIdIn(stores.stream().map(Store::get_id).collect(Collectors.toList()));
    Map<String, List<Food>> stringFoodMap = new HashMap<>();
    foodDetails.forEach(foodDetail -> toMap(stringFoodMap, foodDetail));

    return stores.stream().map(store -> new StoresDto(store, stringFoodMap.get(store.get_id()))).collect(Collectors.toList());
  }

  private void toMap(Map<String, List<Food>> stringFoodMap, FoodDetail foodDetail) {

    if (stringFoodMap.containsKey(foodDetail.getStoreId())) {
      stringFoodMap.get(foodDetail.getStoreId()).add(foodDetail.getFood());
    } else {
      stringFoodMap.put(foodDetail.getStoreId(), Collections.singletonList(foodDetail.getFood()));
    }
  }
}
