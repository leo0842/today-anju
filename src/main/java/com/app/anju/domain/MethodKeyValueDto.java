package com.app.anju.domain;

import lombok.Getter;


@Getter
public class MethodKeyValueDto {

  private final String key;
  private final String value;

  public MethodKeyValueDto(Method method) {
    this.key = method.name();
    this.value = method.getMethodValue();
  }
}
