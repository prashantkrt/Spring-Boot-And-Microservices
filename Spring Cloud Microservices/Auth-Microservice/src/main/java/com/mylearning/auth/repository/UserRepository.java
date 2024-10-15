package com.mylearning.auth.repository;

import com.mylearning.auth.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByUsername(String username);
}
