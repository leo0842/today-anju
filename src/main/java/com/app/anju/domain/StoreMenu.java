package com.app.anju.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreMenu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String storeId;

  private String name;

  @ManyToOne
  private Food food;

  @ManyToOne
  private Method method;

  private Integer price;

  private String characteristic;

  @OneToMany(mappedBy = "storeMenu", cascade = CascadeType.ALL)
  private List<MenuSauce> menuSauce;

  @OneToMany(mappedBy = "storeMenu", cascade = CascadeType.ALL)
  private List<IngredientDetail> ingredientDetails;

  public void addSauce(Sauce sauce) {

    if (this.menuSauce == null) {

      this.menuSauce = new ArrayList<>();
    }
    this
        .menuSauce
        .add(
            MenuSauce
                .builder()
                .sauce(sauce)
                .storeMenu(this)
                .build());
  }

  public void addIngredient(Ingredient ingredient) {

    if (this.ingredientDetails == null) {

      this.ingredientDetails = new ArrayList<>();
    }

    this.ingredientDetails.add(IngredientDetail.builder().ingredient(ingredient).storeMenu(this).build());
  }
}
