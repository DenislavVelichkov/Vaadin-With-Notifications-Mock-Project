package com.example.application.backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.application.backend.data.entity.Employee;
import com.example.application.backend.data.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

  Optional<Notification> findById(Long id);

  List<Notification> findAll();

}
