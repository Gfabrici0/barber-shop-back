package com.br.barbershop.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
  ADMIN("ADMIN"),
  BARBERSHOP("BARBERSHOP"),
  BARBER("BARBER"),
  USER("USER");

  private String role;
}
