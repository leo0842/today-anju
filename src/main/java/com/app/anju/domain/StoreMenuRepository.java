package com.app.anju.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {

  List<StoreMenu> findByStoreIdIn(List<String> storeIds);

}
