package com.example.application.service;

import java.util.Set;

import com.example.application.backend.common.Authorities;
import com.example.application.backend.data.models.RoleDTO;

public interface RoleService {

  void seedRolesInDb();

  Set<RoleDTO> findAllRoles();

  RoleDTO findByAuthority(Authorities authority);
}
