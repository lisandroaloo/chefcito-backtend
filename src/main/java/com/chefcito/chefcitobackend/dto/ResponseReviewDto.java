package com.chefcito.chefcitobackend.dto;

import com.chefcito.chefcitobackend.model.IngredientXRecipe;
import com.chefcito.chefcitobackend.model.Recipe;
import com.chefcito.chefcitobackend.model.ReviewXUserXRecipe;
import com.chefcito.chefcitobackend.model.StepXRecipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseReviewDto {

    private Long id;
    private Long re_id;
    private String us_name;
    private Integer stars;
    private String review;


    public static ResponseReviewDto toResponseDto(ReviewXUserXRecipe review) {
    return ResponseReviewDto.builder()
        .us_name(review.getUser().getUsAlias())
        .review(review.getReview())
        .re_id(review.getRecipe().getRe_id())
        .stars(review.getStars())
        .id(review.getRxuxr_id())
        .build();
  }
}
