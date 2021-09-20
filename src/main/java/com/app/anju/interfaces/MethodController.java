package com.app.anju.interfaces;

import com.app.anju.applications.MethodService;
import com.app.anju.domain.MethodDto;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MethodController {

  private final MethodService methodService;

  @GetMapping("/methods")
  @ApiOperation("음식 조리 방법 목록 조회")
  public List<MethodDto> getMethodValue() {

    return methodService.getValues();
  }

}
