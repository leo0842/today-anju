package com.app.anju.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {

  private String foodName;

  private List<Double> geoBoundary;

  private Base base;

  private Method method;

  private Integer point;

  private Boolean visited;

  private String ingredientName;
}
