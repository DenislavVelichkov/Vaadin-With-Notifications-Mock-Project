package com.example.application.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.application.backend.data.entity.Employee;
import com.example.application.backend.data.entity.CSPNotification;
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

//  getAllEmployees should be in Employees service, but for the demo its here.
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

    Employee notificationReceiver =
        this.employeeRepo
            .findByEmail(receiverEmail)
            .orElseThrow(() -> new IllegalArgumentException("User does not exist!"));

    CSPNotification CSPNotification = new CSPNotification();
    CSPNotification.setContent(content);
    CSPNotification.setTopic(topic);
    CSPNotification.setTime(new Timestamp(new Date().getTime()));
    CSPNotification.setEmployee(notificationReceiver);

    return this.modelMapper.map(
        this.notificationRepo.saveAndFlush(CSPNotification), CSPNotificationDTO.class);
  }

}
