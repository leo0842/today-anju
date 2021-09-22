package com.app.anju.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreKeyValueDto {

  private String id;

  private String name;

  public StoreKeyValueDto(Store store) {

    this.id = store.get_id();
    this.name = store.getName();
  }
}
