package com.app.anju.domain;

import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FoodCreateDto {

  private String name;

  private List<Long> sauces;

  private Long method;

  private String characteristic;

  private Integer price;

  private String store;

  private Long food;

  private List<Long> ingredients;
}
