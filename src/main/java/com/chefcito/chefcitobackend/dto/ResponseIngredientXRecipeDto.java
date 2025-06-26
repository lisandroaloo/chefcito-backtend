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
public class ResponseIngredientXRecipeDto {
  private Long ixr_id;
  private Long ixr_in_id;
  private Long ixr_re_id;
  private Integer quantity;

  public static IngredientXRecipe toIngredientXRecipe(ResponseIngredientXRecipeDto responseIngredientXRecipeDto) {
    return IngredientXRecipe.builder()
        .ixr_id(responseIngredientXRecipeDto.getIxr_id())
        .quantity(responseIngredientXRecipeDto.getQuantity())
        .build();
  }

  public static ResponseIngredientXRecipeDto toResponseIngredientXRecipeDto(IngredientXRecipe ingredientXRecipe) {
    return ResponseIngredientXRecipeDto.builder()
        .ixr_id(ingredientXRecipe.getIxr_id())
        .ixr_in_id(ingredientXRecipe.getIngredient() != null ? ingredientXRecipe.getIngredient().getIn_id() : null)
        .ixr_re_id(ingredientXRecipe.getRecipe() != null ? ingredientXRecipe.getRecipe().getRe_id() : null)
        .quantity(ingredientXRecipe.getQuantity())
        .build();
  }
} 