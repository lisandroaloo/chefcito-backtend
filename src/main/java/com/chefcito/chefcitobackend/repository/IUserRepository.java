package com.chefcito.chefcitobackend.repository;

import com.chefcito.chefcitobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUs_alias(String username);

}
