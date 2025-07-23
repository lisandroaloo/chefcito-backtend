package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.dto.RequestRecipeDto.ingredientsClass;
import com.chefcito.chefcitobackend.model.IngredientXRecipe;
import com.chefcito.chefcitobackend.model.Recipe;
import com.chefcito.chefcitobackend.model.StepXRecipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRecipeDto {
  private Long re_id;
  private Long re_creator_us_id;
  private String re_picture;
  private String re_title;
  private Boolean re_suitable_for_vegan;
  private Boolean re_suitable_for_vegetarian;
  private Boolean re_suitable_for_celiac;
  private Boolean re_suitable_for_lactose_intolerant;
  private Float re_review;
  private List<String> steps;
  private List<ingredientsClass> ingredients;

  public static Recipe toRecipe(ResponseRecipeDto responseRecipeDto) {
    return Recipe.builder()
        .re_id(responseRecipeDto.getRe_id())
        .re_picture(responseRecipeDto.getRe_picture())
        .re_title(responseRecipeDto.getRe_title())
        .re_suitable_for_vegan(responseRecipeDto.getRe_suitable_for_vegan())
        .re_suitable_for_vegetarian(responseRecipeDto.getRe_suitable_for_vegetarian())
        .re_suitable_for_celiac(responseRecipeDto.getRe_suitable_for_celiac())
        .re_suitable_for_lactose_intolerant(responseRecipeDto.getRe_suitable_for_lactose_intolerant())
        .steps(null)
        .ingredients(null)
        .build();
  }

  public static ResponseRecipeDto toResponseRecipeDto(Recipe recipe) {
    return ResponseRecipeDto.builder()
        .re_id(recipe.getRe_id())
        .re_creator_us_id(recipe.getUser() != null ? recipe.getUser().getUs_id() : null)
        .re_picture(recipe.getRe_picture())
        .re_title(recipe.getRe_title())
        .re_suitable_for_vegan(recipe.getRe_suitable_for_vegan())
        .re_suitable_for_vegetarian(recipe.getRe_suitable_for_vegetarian())
        .re_suitable_for_celiac(recipe.getRe_suitable_for_celiac())
        .re_suitable_for_lactose_intolerant(recipe.getRe_suitable_for_lactose_intolerant())
        .steps(recipe.getSteps().stream().map(StepXRecipe::getSxr_description).toList())
        .ingredients(
            recipe.getIngredients().stream()
                .map(i -> new ingredientsClass(i.getName(), i.getQuantity(), i.getUnit()))
                .toList())
        .build();
  }
}