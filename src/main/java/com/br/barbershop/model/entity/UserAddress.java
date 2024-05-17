package com.br.barbershop.model.entity;

import jakarta.persistence.*;

import java.util.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Table(name = "user_address")
@EqualsAndHashCode(of = "id")
public class UserAddress {

  @Id
  @UuidGenerator
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;

  UserAddress(User user, Address address) {
    this.user = user;
    this.address = address;
  }

}
