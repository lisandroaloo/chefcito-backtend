package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestRecipeDto {

  @JsonProperty("us_id")
  private Long re_creator_us_id;

  @JsonProperty("picture")
  private String re_picture;

  @JsonProperty("title")
  private String re_title;

  @JsonProperty("vegan")
  private Boolean re_suitable_for_vegan;

  @JsonProperty("vegetarian")
  private Boolean re_suitable_for_vegetarian;

  @JsonProperty("celiac")
  private Boolean re_suitable_for_celiac;

  @JsonProperty("lactose")
  private Boolean re_suitable_for_lactose_intolerant;

  @JsonProperty("ingredients")
  private List<ingredientsClass> ingredients;

  @JsonProperty("steps")
  private List<String> steps;
  
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ingredientsClass {
    private String name;
    private String quantity;
    private String unit;
  }

  public static Recipe toRecipe(RequestRecipeDto dto) {
    return new Recipe(
        null, // id
        null, // creator (lo podrías setear usando re_creator_us_id con el repositorio)
        dto.getRe_picture(),
        dto.getRe_title(),
        dto.getRe_suitable_for_vegan(),
        dto.getRe_suitable_for_vegetarian(),
        dto.getRe_suitable_for_celiac(),
        dto.getRe_suitable_for_lactose_intolerant(),
        null, // ingredientes
        null, // pasos
        null,
        null);
  }
}
