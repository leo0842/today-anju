package com.app.anju.domain;

import lombok.Getter;

@Getter
public class BaseKeyValueDto {

  private final String key;
  private final String value;

  public BaseKeyValueDto(Base base) {
    this.key = base.name();
    this.value = base.getBaseValue();
  }

}
