package com.app.anju.domain;

import lombok.Getter;

@Getter
public class SauceDto {

  private final Long id;
  private final String name;

  public SauceDto(Sauce sauce) {
    this.id = sauce.getId();
    this.name = sauce.getName();
  }

}
