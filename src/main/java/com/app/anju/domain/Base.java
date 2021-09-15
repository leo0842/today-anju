package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Base {

  SOY_SOURCE("간장"),
  RED_PEPPER_SOURCE("고추장");

  private String baseValue;

}
