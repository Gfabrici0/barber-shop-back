package com.br.barbershop.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum StatusEnum {
  ACTIVE("ACTIVE"),
  INACTIVE("INACTIVE"),
  PENDING_APPROVAL("PENDING_APPROVAL"),
  REJECTED("REJECTED");

  private String status;
}
