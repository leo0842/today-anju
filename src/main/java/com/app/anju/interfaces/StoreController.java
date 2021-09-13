package com.app.anju.interfaces;

import com.app.anju.applications.StoreService;
import com.app.anju.domain.StoresDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @PostMapping("/store")
  @ResponseStatus(HttpStatus.CREATED)
  public void insertData(
      @RequestBody StoresDto storesDto
  ) {

    storeService.insertStore(storesDto);
  }

  @GetMapping("/stores")
  public List<StoresDto> getStores() {

    return storeService.getStores();
  }
}
