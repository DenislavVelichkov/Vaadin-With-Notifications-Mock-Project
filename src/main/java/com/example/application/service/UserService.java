package com.example.application.service;

import java.util.List;

import com.example.application.backend.data.models.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  void registerUser(UserDTO userDTO);

  UserDTO findUserByUsername(String username);

  UserDTO findUserByEmail(String email);

  List<UserDTO> findAllUsers();

  void setUserRole(Long id, String role);
}
