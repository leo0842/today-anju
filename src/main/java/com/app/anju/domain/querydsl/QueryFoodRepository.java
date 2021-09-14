package com.app.anju.domain.querydsl;

import com.app.anju.domain.Base;
import com.app.anju.domain.FilterDto;
import com.app.anju.domain.Food;
import com.app.anju.domain.FoodDetail;
import com.app.anju.domain.Method;
import com.app.anju.domain.QFood;
import com.app.anju.domain.QFoodDetail;
import com.app.anju.domain.QIngredient;
import com.app.anju.domain.QIngredientDetail;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueryFoodRepository {

  private final JPAQueryFactory query;

  public List<FoodDetail> getFilteredFoodDetail(FilterDto filterDto, List<String> storeIds) {

    QFood food = QFood.food;
    QFoodDetail foodDetail = QFoodDetail.foodDetail;
    QIngredientDetail ingredientDetail = QIngredientDetail.ingredientDetail;

    return query
        .select(foodDetail)
        .from(foodDetail)
        .leftJoin(foodDetail.food, food)
        .leftJoin(food.ingredientDetails, ingredientDetail)
        .where(eqStoreIds(foodDetail, storeIds))
        .where(eqBase(food, filterDto.getBase()))
        .where(eqMethod(food, filterDto.getMethod()))
        .where(eqIngredient(ingredientDetail, filterDto.getIngredientName()))
        .fetch();
  }

  private BooleanExpression eqStoreIds(QFoodDetail foodDetail, List<String> storeIds) {

    return foodDetail.storeId.in(storeIds);
  }

  private Predicate eqBase(QFood food, Base base) {

    if (base == null) {

      return null;
    }

    return food.base.eq(base);
  }

  private Predicate eqMethod(QFood food, Method method) {

    if (method == null) {

      return null;
    }

    return food.method.eq(method);
  }

  private Predicate eqIngredient(QIngredientDetail ingredientDetail, String ingredientName) {

    if (ingredientName == null) {

      return null;
    }

    return ingredientDetail.ingredient.name.eq(ingredientName);
  }
}
