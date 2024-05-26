package com.br.barbershop.model.DTO.address;

import com.br.barbershop.model.entity.Address;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record DataAddress(
    UUID id,
    @Pattern(regexp = "^\\d{5}-\\d{3}$\n", message = "CEP não deve conter hífen")
    String addressNumber,
    String addressStreet,
    String addressCity
) {
  public DataAddress(Address address){
    this(address.getId(), address.getAddressNumber(), address.getAddressStreet(), address.getAddressCity());
  }
}
