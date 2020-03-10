package com.example.application.backend.repository;

import java.util.Optional;

import com.example.application.backend.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);
}
