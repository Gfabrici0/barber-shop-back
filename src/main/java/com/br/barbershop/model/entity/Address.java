package com.br.barbershop.model.entity;

import com.br.barbershop.help.StringUtil;
import com.br.barbershop.model.DTO.DataRegisterAddress;
import com.br.barbershop.model.DTO.DataUpdateAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
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

  public String getFormattedAddressNumber() {
    return StringUtil.formatZipCode(addressNumber);
  }
}
