package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestRecipeDto {
  private Long re_creator_us_id;
  private String re_picture;
  private String re_title;
  private Boolean re_suitable_for_vegan;
  private Boolean re_suitable_for_vegetarian;
  private Boolean re_suitable_for_celiac;
  private Boolean re_suitable_for_lactose_intolerant;

  public static Recipe toRecipe(RequestRecipeDto requestRecipeDto) {
    return new Recipe(null, null, requestRecipeDto.getRe_picture(), requestRecipeDto.getRe_title(),
        requestRecipeDto.getRe_suitable_for_vegan(), requestRecipeDto.getRe_suitable_for_vegetarian(),
        requestRecipeDto.getRe_suitable_for_celiac(), requestRecipeDto.getRe_suitable_for_lactose_intolerant(),
        null, null);
  }
} 