package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.dto.ReviewRecipeDto;
import com.chefcito.chefcitobackend.model.ReviewXUserXRecipe;
import com.chefcito.chefcitobackend.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IReviewXUserXRecipeRepository extends JpaRepository<ReviewXUserXRecipe, Long> {

    @Query("SELECT r FROM ReviewXUserXRecipe r WHERE r.recipe.re_id = :id")
    List<ReviewXUserXRecipe> findAllByReviewId(@Param("id") Long id);

    @Query("SELECT r FROM ReviewXUserXRecipe r WHERE r.recipe.re_id = :recipeId AND r.user.us_id = :userId")
    ReviewXUserXRecipe checkReview(@Param("recipeId") Long recipeId, @Param("userId") Long userId);

}
