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
public class ResponseIngredientDto {
  private Long in_id;
  private String in_name;

  public static Ingredient toIngredient(ResponseIngredientDto responseIngredientDto) {
    return new Ingredient(responseIngredientDto.getIn_id(), responseIngredientDto.getIn_name(), null);
  }


  public static ResponseIngredientDto toResponseIngredientDto(Ingredient ingredient) {
    return new ResponseIngredientDto(ingredient.getIn_id(), ingredient.getIn_name());
  }
}
