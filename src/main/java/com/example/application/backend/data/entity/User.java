package com.example.application.backend.data.entity;

import java.util.Set;
import javax.persistence.*;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

  @Column(
      name = "username",
      nullable = false,
      unique = true)
  private String username;

  @Column(
      name = "first_name",
      nullable = false)
  private String firstName;

  @Column(
      name = "last_name",
      nullable = false)
  private String lastName;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(
      name = "email",
      nullable = false,
      unique = true)
  private String email;

  @OneToOne(
      targetEntity = Employee.class,
      mappedBy = "userCredentials",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Employee employee;

  @ManyToMany(targetEntity = Role.class,
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(
          name = "user_id",
          referencedColumnName = "id"
      ),
      inverseJoinColumns = @JoinColumn(
          name = "role_id",
          referencedColumnName = "id"
      )
  )
  private Set<Role> authorities;

  @Override
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

  @Override
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

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  @Override
  public Set<Role> getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(Set<Role> authorities) {
    this.authorities = authorities;
  }

  @Override
  @Transient
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @Transient
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @Transient
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @Transient
  public boolean isEnabled() {
    return true;
  }
}

