package com.app.anju.interfaces;

import com.app.anju.applications.SauceService;
import com.app.anju.domain.SauceDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SauceController {

  private final SauceService sauceService;

  @GetMapping("/sauces")
  @ApiOperation("음식 베이스 목록 조회")
  public List<SauceDto> getBaseValue() {

    return sauceService.getValues();
  }

}
