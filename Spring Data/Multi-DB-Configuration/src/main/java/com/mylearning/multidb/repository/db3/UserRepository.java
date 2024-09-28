package com.mylearning.multidb.repository.db3;

import com.mylearning.multidb.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
