package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeRepository extends JpaRepository<Recipe, Long> {
}
