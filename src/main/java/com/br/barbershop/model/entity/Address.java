package com.br.barbershop.model.entity;

import com.br.barbershop.help.StringUtil;
import com.br.barbershop.model.DTO.address.DataRegisterAddress;
import com.br.barbershop.model.DTO.address.DataUpdateAddress;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

  @Id
  @UuidGenerator
  private UUID id;

  @Column(name = "address_number")
  private String addressNumber;

  @Column(name = "address_street")
  private String addressStreet;

  @Column(name = "address_city")
  private String addressCity;

  public Address(DataRegisterAddress dataRegisterAddress) {
    this.addressNumber = dataRegisterAddress.addressNumber();
    this.addressStreet = dataRegisterAddress.addressStreet();
    this.addressCity = dataRegisterAddress.addressCity();
  }

  public void updateAddress(DataUpdateAddress dataUpdateAddress) {
    if (dataUpdateAddress.addressStreet() != null && !dataUpdateAddress.addressCity().isEmpty()) {
      this.setAddressStreet(dataUpdateAddress.addressStreet());
    }
    if (dataUpdateAddress.addressNumber() != null && !dataUpdateAddress.addressNumber().isEmpty()) {
      this.setAddressNumber(dataUpdateAddress.addressNumber());
    }
    if (dataUpdateAddress.addressCity() != null && !dataUpdateAddress.addressCity().isEmpty()) {
      this.setAddressCity(dataUpdateAddress.addressCity());
    }
  }
}
