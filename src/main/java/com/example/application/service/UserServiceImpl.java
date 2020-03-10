package com.example.application.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.example.application.backend.common.Authorities;
import com.example.application.backend.common.ExceptionMessages;
import com.example.application.backend.data.entity.Employee;
import com.example.application.backend.data.entity.User;
import com.example.application.backend.data.models.UserDTO;
import com.example.application.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleService roleService;
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired()
  public UserServiceImpl(UserRepository userRepository,
                         RoleService roleService,
                         ModelMapper modelMapper,
                         BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleService = roleService;
    this.modelMapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.userRepository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.USERNAME_NOT_FOUND));
  }

  @Override
  public void registerUser(UserDTO userDTO) {
    this.roleService.seedRolesInDb();

    if (this.userRepository.count() == 0) {
      userDTO.setAuthorities(this.roleService.findAllRoles());
    } else {
      userDTO.setAuthorities(new LinkedHashSet<>());
      userDTO.getAuthorities()
                      .add(this.roleService.findByAuthority(Authorities.USER));
    }

    userDTO.setPassword(
        this.passwordEncoder.encode(userDTO.getPassword()));

    User user = this.modelMapper.map(userDTO, User.class);
    this.modelMapper.validate();

    Employee employeeProfile = new Employee();
    employeeProfile.setEmail(user.getEmail());
    employeeProfile.setFirstname(user.getFirstName());
    employeeProfile.setLastname(user.getLastName());
    employeeProfile.setUserCredentials(user);
// TODO: 3/10/2020 Implement Title of the employee
    user.setEmployee(employeeProfile);

    this.userRepository.saveAndFlush(user);
  }

  @Override
  public UserDTO findUserByUsername(String username) {
    return this.userRepository
        .findByUsername(username)
        .map(user -> this.modelMapper.map(user, UserDTO.class))
        .orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.USERNAME_NOT_FOUND));
  }

  @Override
  public UserDTO findUserByEmail(String email) {
    return this.userRepository
        .findByEmail(email)
        .map(user -> this.modelMapper.map(user, UserDTO.class))
        .orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.USER_NOT_FOUND));
  }

  @Override
  public List<UserDTO> findAllUsers() {
    return this.userRepository
        .findAll()
        .stream()
        .map(user -> this.modelMapper.map(user, UserDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public void setUserRole(String id, String role) {
    User user = this.userRepository.findById(id)
                                   .orElseThrow(() ->
                                       new IllegalArgumentException(
                                           ExceptionMessages.INCORRECT_ID));

    UserDTO userServiceModel =
        this.modelMapper.map(user, UserDTO.class);
    this.modelMapper.validate();

    userServiceModel.getAuthorities().clear();

    switch (role) {
      case "user":
        userServiceModel
            .getAuthorities()
            .add(this.roleService.findByAuthority(Authorities.USER));
        break;
      case "moderator":
        userServiceModel
            .getAuthorities()
            .add(this.roleService.findByAuthority(Authorities.USER));
        userServiceModel
            .getAuthorities()
            .add(this.roleService.findByAuthority(Authorities.MODERATOR));
        break;
      case "admin":
        userServiceModel
            .getAuthorities()
            .add(this.roleService.findByAuthority(Authorities.USER));
        userServiceModel
            .getAuthorities()
            .add(this.roleService.findByAuthority(Authorities.MODERATOR));
        userServiceModel
            .getAuthorities()
            .add(this.roleService.findByAuthority(Authorities.ADMIN));
        break;
      default:
        break;
    }

    this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
  }
}
