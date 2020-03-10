package com.example.application.service;

import java.util.List;

import com.example.application.backend.data.models.CSPNotificationDTO;
import com.example.application.backend.data.models.EmployeeDTO;

public interface CSPNotificationService {

  List<EmployeeDTO> getAllEmployees();

  List<CSPNotificationDTO> getAllNotifications();

  CSPNotificationDTO createNotification(String receiverEmail,
                                        String topic,
                                        String content);
}
