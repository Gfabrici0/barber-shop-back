package com.br.barbershop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Table(name = "barbershop_address")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class BarbershopAddress {

  @Id
  @UuidGenerator
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "barbershop_id")
  private Barbershop barbershop;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;

  BarbershopAddress(Barbershop barbershop, Address address) {
    this.barbershop = barbershop;
    this.address = address;
  }

}
