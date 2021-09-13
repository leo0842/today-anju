package com.app.anju.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDetailRepository extends JpaRepository<FoodDetail, Long> {

  List<FoodDetail> findByStoreIdIn(List<String> storeIds);

}
