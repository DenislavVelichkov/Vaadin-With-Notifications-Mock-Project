package com.example.application.service;

import java.util.List;

import com.example.application.backend.data.models.EmployeeDTO;
import com.example.application.backend.data.models.CSPNotificationDTO;

public interface CSPNotificationService {

  List<EmployeeDTO> getAllEmployees();

  List<CSPNotificationDTO> getAllNotifications();

  CSPNotificationDTO createNotification(String receiverEmail,
                                        String topic,
                                        String content);
}
