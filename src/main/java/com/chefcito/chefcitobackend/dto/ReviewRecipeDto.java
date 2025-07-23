package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.Recipe;
import com.chefcito.chefcitobackend.model.ReviewXUserXRecipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRecipeDto {
    private Long re_id;
    private Long us_id;
    private Integer stars;
    private String review;

}
