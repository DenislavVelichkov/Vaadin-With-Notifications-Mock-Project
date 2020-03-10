package com.example.application.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.application.backend.common.Authorities;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

  @Enumerated(EnumType.STRING)
  private Authorities authority;

  public Role() {
  }

  public Role(Authorities authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return this.authority.name();
  }

  public void setAuthority(Authorities authority) {
    this.authority = authority;
  }

}
