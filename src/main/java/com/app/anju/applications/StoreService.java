package com.app.anju.applications;

import com.app.anju.domain.Food;
import com.app.anju.domain.StoreDto;
import com.app.anju.domain.Store;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final MongoTemplate mongoTemplate;

  public void insertStore(StoreDto storeDto) {

    Store store = Store.builder()
        .name(storeDto.getName())
        .longitude(storeDto.getLongitude())
        .latitude(storeDto.getLatitude())
        .menus(storeDto.getMenu())
        .point(storeDto.getPoint())
        .visited(storeDto.isVisited())
        .build();
    mongoTemplate.insert(store);
  }

  public List<Store> getStores() {

    return mongoTemplate.findAll(Store.class);
  }
}
