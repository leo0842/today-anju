package com.app.anju.domain;

import lombok.Getter;


@Getter
public class MethodDto {

  private final Long id;
  private final String name;

  public MethodDto(Method method) {
    this.id = method.getId();
    this.name = method.getName();
  }
}
