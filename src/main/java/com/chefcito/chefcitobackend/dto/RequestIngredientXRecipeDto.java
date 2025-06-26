package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.IngredientXRecipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestIngredientXRecipeDto {
  private Long ixr_in_id;
  private Long ixr_re_id;
  private Integer quantity;

  public static IngredientXRecipe toIngredientXRecipe(RequestIngredientXRecipeDto requestIngredientXRecipeDto) {
    return new IngredientXRecipe(null, null, null, requestIngredientXRecipeDto.getQuantity());
  }
} 