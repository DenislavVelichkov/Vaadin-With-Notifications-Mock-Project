package com.example.application.backend.data.models;

import com.example.application.backend.common.Authorities;

public class RoleDTO {

  private Long id;
  private Authorities authority;

  public RoleDTO() {
  }

  public Authorities getAuthority() {
    return this.authority;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setAuthority(Authorities authority) {
    this.authority = authority;
  }
}
