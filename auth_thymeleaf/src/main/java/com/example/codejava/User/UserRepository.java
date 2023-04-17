package com.example.codejava.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT user FROM UserEntity user WHERE user.email = ?1")
    UserEntity findByEmail(String email);
}
