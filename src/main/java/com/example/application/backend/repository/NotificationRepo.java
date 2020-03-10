package com.example.application.backend.repository;

import java.util.Optional;

import com.example.application.backend.data.entity.CSPNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<CSPNotification, Long> {

  Optional<CSPNotification> findById(Long id);

}
