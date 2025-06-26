package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, Long> {

   @Query("SELECT u FROM User u WHERE u.usAlias = :alias")
    User findByUsernameee(@Param("alias") String alias);


}
