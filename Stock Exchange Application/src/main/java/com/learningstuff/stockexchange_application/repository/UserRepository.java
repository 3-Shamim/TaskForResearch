package com.learningstuff.stockexchange_application.repository;

import com.learningstuff.stockexchange_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
}
