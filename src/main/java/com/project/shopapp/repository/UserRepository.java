package com.project.shopapp.repository;

import com.project.shopapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<UserModel> findByPhoneNumber(String phoneNumber);
}
