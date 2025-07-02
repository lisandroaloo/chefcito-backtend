package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.StepXRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStepXRecipe extends JpaRepository<StepXRecipe, Long> {
}
