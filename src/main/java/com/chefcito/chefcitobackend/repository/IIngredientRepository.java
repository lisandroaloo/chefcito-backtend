package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {
}
