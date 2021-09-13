package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum IngredientCategory {

  PORK_MEAT("돼지고기"),
  PORK_INTESTINE("돼지내장"),
  CHICKEN_MEAT("닭고기"),
  CHICKEN_INTESTINE("닭내장"),
  BEEF_MEAT("소고기"),
  BEEF_INTESTINE("소내장");

  private String name;


}
