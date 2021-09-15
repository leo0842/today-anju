package com.app.anju.interfaces;

import com.app.anju.applications.FoodService;
import com.app.anju.domain.Base;
import com.app.anju.domain.BaseKeyValueDto;
import com.app.anju.domain.FoodCreateDto;
import com.app.anju.domain.Method;
import com.app.anju.domain.MethodKeyValueDto;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {

  private final FoodService foodService;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public void addFood(
      @RequestBody FoodCreateDto foodCreateDto
  ) {

    foodService.addFood(foodCreateDto);
  }

  @GetMapping("/bases")
  @ApiOperation("음식 베이스 목록 조회")
  public List<BaseKeyValueDto> getBaseValue() {

    return Arrays
        .stream(Base.values())
        .map(BaseKeyValueDto::new)
        .collect(Collectors.toList());
  }

  @GetMapping("/methods")
  @ApiOperation("음식 조리 방법 목록 조회")
  public List<MethodKeyValueDto> getMethodValue() {

    return Arrays
        .stream(Method.values())
        .map(MethodKeyValueDto::new)
        .collect(Collectors.toList());
  }
}
