package com.plaksin.authservice.repository;

import com.plaksin.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select count(u) from User u where u.email = :email")
    int checkUserByEmail(@Param("email") String email);
}
