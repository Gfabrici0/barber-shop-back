package com.br.barbershop.service;

import com.br.barbershop.exception.RoleNotFoundException;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public Role findByRole(String role) {
    return roleRepository.findByRole(role)
      .orElseThrow(() -> new RoleNotFoundException("Role not Found"));
  }

}
