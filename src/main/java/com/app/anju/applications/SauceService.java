package com.app.anju.applications;

import com.app.anju.domain.SauceDto;
import com.app.anju.domain.SauceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SauceService {

  private final SauceRepository sauceRepository;

  public List<SauceDto> getValues() {

    return sauceRepository.findAll().stream().map(SauceDto::new).collect(Collectors.toList());
  }
}
