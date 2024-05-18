package com.br.barbershop.model.DTO.role;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.model.entity.Role;

import java.util.UUID;

public record DataRole(
  UUID id,
  RoleEnum role
) {
  public DataRole(Role role) {
    this(role.getId(), role.getRole());
  }
}
