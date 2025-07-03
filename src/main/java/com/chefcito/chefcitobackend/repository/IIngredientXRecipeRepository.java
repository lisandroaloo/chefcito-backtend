package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.IngredientXRecipe;
import com.chefcito.chefcitobackend.model.Recipe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientXRecipeRepository extends JpaRepository<IngredientXRecipe, Long> {
    @Transactional
    void deleteAllByRecipe(Recipe recipe);
}
