package com.br.barbershop.model.entity;

import com.br.barbershop.model.DTO.DataAdress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "addresses")
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

  public Address(DataAdress dataAdress) {
    this.addressNumber = dataAdress.addressNumber();
    this.addressStreet = dataAdress.addressStreet();
    this.addressCity = dataAdress.addressCity();
  }
}
