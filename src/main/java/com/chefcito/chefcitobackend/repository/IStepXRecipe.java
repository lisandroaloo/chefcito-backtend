package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.Recipe;
import com.chefcito.chefcitobackend.model.StepXRecipe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStepXRecipe extends JpaRepository<StepXRecipe, Long> {
    @Transactional
    void deleteAllByRecipe(Recipe recipe);
}
