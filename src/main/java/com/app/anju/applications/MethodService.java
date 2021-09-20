package com.app.anju.applications;

import com.app.anju.domain.MethodDto;
import com.app.anju.domain.MethodRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MethodService {

  private final MethodRepository methodRepository;

  public List<MethodDto> getValues() {

    return methodRepository.findAll().stream().map(MethodDto::new).collect(Collectors.toList());
  }
}
