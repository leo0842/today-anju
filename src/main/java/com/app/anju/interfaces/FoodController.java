package com.app.anju.interfaces;

import com.app.anju.applications.FoodService;
import com.app.anju.domain.FoodCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FoodController {

  private final FoodService foodService;

  @PostMapping("/food")
  @ResponseStatus(HttpStatus.CREATED)
  public void addFood(
      @RequestBody FoodCreateDto foodCreateDto
  ) {

    foodService.addFood(foodCreateDto);
  }
}
