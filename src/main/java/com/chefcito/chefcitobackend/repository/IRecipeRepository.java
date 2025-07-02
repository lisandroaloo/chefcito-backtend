package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE r.user.us_id = :id")
    List<Recipe> findAllByUserId(@Param("id") Long id);

}

