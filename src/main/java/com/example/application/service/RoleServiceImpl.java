package com.example.application.service;

import java.util.Set;
import java.util.stream.Collectors;


import com.example.application.backend.common.Authorities;
import com.example.application.backend.data.entity.Role;
import com.example.application.backend.data.models.RoleDTO;
import com.example.application.backend.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository,
                         ModelMapper modelMapper) {
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public void seedRolesInDb() {
    if (this.roleRepository.count() == 0) {
      this.roleRepository.saveAndFlush(new Role(Authorities.ADMIN));
      this.roleRepository.saveAndFlush(new Role(Authorities.MODERATOR));
      this.roleRepository.saveAndFlush(new Role(Authorities.ROOT));
      this.roleRepository.saveAndFlush(new Role(Authorities.USER));
    }
  }

  @Override
  public Set<RoleDTO> findAllRoles() {
    return this.roleRepository.findAll()
                              .stream()
                              .map(role -> this.modelMapper.map(role, RoleDTO.class))
                              .collect(Collectors.toSet());
  }

  @Override
  public RoleDTO findByAuthority(Authorities authority) {
    return this.modelMapper
        .map(this.roleRepository.findByAuthority(authority), RoleDTO.class);
  }

}
