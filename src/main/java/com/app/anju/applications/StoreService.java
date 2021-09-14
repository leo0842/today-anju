package com.app.anju.applications;

import com.app.anju.domain.FilterDto;
import com.app.anju.domain.FoodDetail;
import com.app.anju.domain.FoodDetailRepository;
import com.app.anju.domain.Store;
import com.app.anju.domain.StoresDto;
import com.app.anju.domain.querydsl.QueryFoodRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final MongoTemplate mongoTemplate;
  private final FoodDetailRepository foodDetailRepository;
  private final QueryFoodRepository queryFoodRepository;

  public void insertStore(StoresDto storesDto) {

    Store store = mongoTemplate.insert(storesDto.toStore());
  }

  public List<StoresDto> getStores(FilterDto filterDto) {

    List<Store> stores;

    if (filterDto.getGeoBoundary() == null) {

      stores = mongoTemplate.findAll(Store.class);
    } else {

      Point leftBottom = new Point(filterDto.getGeoBoundary().get(0), filterDto.getGeoBoundary().get(1));
      Point rightTop = new Point(filterDto.getGeoBoundary().get(2), filterDto.getGeoBoundary().get(3));
      Box box = new Box(leftBottom, rightTop);

      Query condition = new Query(Criteria.where("location").within(box));

      if (filterDto.getPoint() != null) {
        condition.addCriteria(Criteria.where("point").gte(filterDto.getPoint()));
      }

      if (filterDto.getVisited() != null) {
        condition.addCriteria(Criteria.where("visited").is(filterDto.getVisited()));
      }

      stores = mongoTemplate.find(condition, Store.class);
    }

    List<String> storeIds = stores.stream().map(Store::get_id).collect(Collectors.toList());
    List<FoodDetail> filteredFoodDetail = queryFoodRepository.getFilteredFoodDetail(filterDto, storeIds);

    if (filteredFoodDetail.isEmpty()) {

      return Collections.emptyList();
    }

    HashMap<String, ArrayList<FoodDetail>> mappedStoreIdAndFoodDetails = filteredFoodDetail.stream()
        .collect(
            Collectors.groupingBy(
                FoodDetail::getStoreId,
                HashMap::new,
                Collectors.toCollection(ArrayList::new)
            )
        );

    return stores.stream().map(store ->
        new StoresDto(
            store,
            mappedStoreIdAndFoodDetails.get(store.get_id()).stream().map(FoodDetail::getFood)
                .collect(Collectors.toList())
        )
    )
        .collect(Collectors.toList());
  }
}