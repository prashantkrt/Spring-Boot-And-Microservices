package com.mylearning.springbootmockmvc2.repository;

import com.mylearning.springbootmockmvc2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
