package com.app.anju.interfaces;

import com.app.anju.applications.StoreService;
import com.app.anju.domain.FilterDto;
import com.app.anju.domain.StoreCreateDto;
import com.app.anju.domain.StoreResponseDto;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @GetMapping("/stores")
  public List<StoreResponseDto> getStores(
      FilterDto filterDto
  ) {

    return storeService.getStores(filterDto);
  }

  @PostMapping("/store")
  public void addStore(
      @RequestBody StoreCreateDto storeCreateDto
  ) throws MalformedURLException, UnsupportedEncodingException {

    storeService.insertStore(storeCreateDto);
  }

}
