package com.example.application.backend.data.models;

import java.util.Set;

public class UserDTO {

  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private String email;
  private Set<RoleDTO> authorities;


  public UserDTO() {
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<RoleDTO> getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(Set<RoleDTO> authorities) {
    this.authorities = authorities;
  }
}
