package com.app.anju.domain.querydsl;

import com.app.anju.domain.FilterDto;
import com.app.anju.domain.QIngredientDetail;
import com.app.anju.domain.QMenuSauce;
import com.app.anju.domain.QStoreMenu;
import com.app.anju.domain.StoreMenu;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueryFoodRepository {

  private final JPAQueryFactory query;

  public List<StoreMenu> getFilteredFoodDetail(FilterDto filterDto, List<String> storeIds) {

    QStoreMenu storeMenu = QStoreMenu.storeMenu;
    QMenuSauce menuSauce = QMenuSauce.menuSauce;
    QIngredientDetail ingredientDetail = QIngredientDetail.ingredientDetail;
    return query
        .selectDistinct(storeMenu)
        .from(storeMenu)
        .leftJoin(storeMenu.menuSauce, menuSauce)
        .leftJoin(storeMenu.ingredientDetails, ingredientDetail)
        .where(eqStoreIds(storeMenu, storeIds))
        .where(eqSauce(menuSauce, filterDto.getSauce()))
        .where(eqMethod(storeMenu, filterDto.getMethod()))
        .where(includeIngredientName(ingredientDetail, filterDto.getIngredientName()))
        .where(includeFoodName(storeMenu, filterDto.getFoodName()))
        .fetch();
  }

  private BooleanExpression eqStoreIds(QStoreMenu storeMenu, List<String> storeIds) {

    return storeMenu.storeId.in(storeIds);
  }

  private BooleanExpression eqSauce(QMenuSauce menuSauce, Long sauceId) {

    if (sauceId == null) {

      return null;
    }

    return menuSauce.sauce.id.eq(sauceId);
  }

  private BooleanExpression eqMethod(QStoreMenu storeMenu, Long methodId) {

    if (methodId == null) {

      return null;
    }

    return storeMenu.method.id.eq(methodId);
  }

  private BooleanExpression includeIngredientName(QIngredientDetail ingredientDetail, String ingredientName) {

    if (ingredientName == null) {

      return null;
    }

    return ingredientDetail.ingredient.name.contains(ingredientName);
  }

  private BooleanExpression includeFoodName(QStoreMenu storeMenu, String foodName) {

    if (foodName == null) {

      return null;
    }

    return storeMenu.food.name.contains(foodName);
  }
}
