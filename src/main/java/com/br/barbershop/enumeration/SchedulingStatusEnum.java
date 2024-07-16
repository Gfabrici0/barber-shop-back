package com.br.barbershop.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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

  @JsonCreator
  public static SchedulingStatusEnum forValue(String value) {
    for (SchedulingStatusEnum statusEnum : SchedulingStatusEnum.values()) {
      if (statusEnum.getStatus().equals(value)) {
        return statusEnum;
      }
    }
    throw new IllegalArgumentException("Invalid status value: " + value);
  }

  @JsonValue
  public String toValue() {
    return getStatus();
  }
}
