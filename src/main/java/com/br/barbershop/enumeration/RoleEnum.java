package com.br.barbershop.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RoleEnum {
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_BARBERSHOP("ROLE_BARBERSHOP"),
  ROLE_BARBER("ROLE_BARBER"),
  ROLE_USER("ROLE_USER");

  private String role;
}
