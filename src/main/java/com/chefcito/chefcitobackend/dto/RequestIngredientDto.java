package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestIngredientDto {
  private String in_name;

  public static Ingredient toIngredient(RequestIngredientDto responseIngredientDto) {
    return new Ingredient(null, responseIngredientDto.getIn_name(), null);
  }
}
