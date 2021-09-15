package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Method {

  FRY("튀김"),
  SOUP("탕"),
  STIR("볶음");

  private String methodValue;
}
