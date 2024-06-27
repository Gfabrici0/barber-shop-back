package com.br.barbershop.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum SchedulingStatusEnum {
  PENDING_BARBER_APPROVAL("PENDING_BARBER_APPROVAL"),
  BARBER_APPROVED("BARBER_APPROVED"),
  BARBER_REJECTED("BARBER_REJECTED"),
  USER_CANCELED("USER_CANCELED"),
  BARBER_CANCELED("BARBER_CANCELED"),
  USER_NOT_FOUND("USER_NOT_FOUND"),
  DONE("DONE");

  private String status;
}
