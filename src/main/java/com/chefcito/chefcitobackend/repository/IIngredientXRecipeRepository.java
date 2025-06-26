package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.IngredientXRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientXRecipeRepository extends JpaRepository<IngredientXRecipe, Long> {
}
