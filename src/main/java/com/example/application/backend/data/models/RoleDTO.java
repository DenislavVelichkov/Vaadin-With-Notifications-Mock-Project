package com.example.application.backend.data.models;

import com.example.application.backend.common.Authorities;

public class RoleDTO {

  private Authorities authority;

  public RoleDTO() {
  }

  public Authorities getAuthority() {
    return this.authority;
  }

  public void setAuthority(Authorities authority) {
    this.authority = authority;
  }
}
