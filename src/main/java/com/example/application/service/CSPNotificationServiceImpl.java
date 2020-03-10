package com.example.application.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.application.backend.data.entity.Employee;
import com.example.application.backend.data.entity.Notification;
import com.example.application.backend.data.models.EmployeeDTO;
import com.example.application.backend.data.models.CSPNotificationDTO;
import com.example.application.backend.repository.EmployeeRepo;
import com.example.application.backend.repository.NotificationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CSPNotificationServiceImpl implements CSPNotificationService {

  private final EmployeeRepo employeeRepo;
  private final NotificationRepo notificationRepo;
  private final ModelMapper modelMapper;

  @Autowired
  public CSPNotificationServiceImpl(EmployeeRepo employeeRepo,
                                    NotificationRepo notificationRepo,
                                    ModelMapper modelMapper) {
    this.employeeRepo = employeeRepo;
    this.notificationRepo = notificationRepo;
    this.modelMapper = modelMapper;
  }


  @Override
  public List<EmployeeDTO> getAllEmployees() {

    return this.employeeRepo
        .findAll()
        .stream()
        .map(e -> this.modelMapper.map(e, EmployeeDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<CSPNotificationDTO> getAllNotifications() {

    return this.notificationRepo
        .findAll()
        .stream()
        .map(n -> this.modelMapper.map(n, CSPNotificationDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public CSPNotificationDTO createNotification(String receiverEmail,
                                               String topic,
                                               String content) {

    Employee receiver =
        this.employeeRepo
            .findByEmail(receiverEmail)
            .orElseThrow(() -> new IllegalArgumentException("User does not exist"));

    Notification notification = new Notification();
    notification.setContent(content);
    notification.setTopic(topic);
    notification.setTime(new Timestamp(new Date().getTime()));
    notification.setEmployee(receiver);

    return this.modelMapper.map(
        this.notificationRepo.saveAndFlush(notification), CSPNotificationDTO.class);
  }

}
