package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.PendingRecipeXUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPendingRecipeXUserRepository extends JpaRepository<PendingRecipeXUser, Long> {
}
