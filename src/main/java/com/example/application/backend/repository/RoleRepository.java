package com.example.application.backend.repository;

import com.example.application.backend.common.Authorities;
import com.example.application.backend.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

  Role findByAuthority(Authorities authority);

}
