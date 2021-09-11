package com.app.anju.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Food {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String foodName;

  private Base base;

  private Method method;

  private String characteristic;

  private Integer price;

  @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
  private List<FoodDetail> foodDetails;

  @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
  private List<IngredientDetail> ingredientDetails;
}
