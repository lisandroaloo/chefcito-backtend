package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.PendingRecipeXUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPendingRecipeXUserRepository extends JpaRepository<PendingRecipeXUser, Long> {

    @Query("SELECT prxu FROM PendingRecipeXUser prxu WHERE prxu.user.id = :id AND prxu.recipe.id = :re_id")
    PendingRecipeXUser findByUserIdAndRecipeId(@Param("id") Long id, @Param("re_id") Long re_id);

    @Query("SELECT prxu FROM PendingRecipeXUser prxu WHERE prxu.user.id = :id")
    List<PendingRecipeXUser> findByUserId(@Param("id") Long id);
}
