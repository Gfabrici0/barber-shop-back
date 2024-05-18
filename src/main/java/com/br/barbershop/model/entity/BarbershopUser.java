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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barbershop_user")
@EqualsAndHashCode(of = "id")
public class BarbershopUser {

  @Id
  @UuidGenerator
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "barbershop_id", nullable = false)
  private Barbershop barbershop;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  BarbershopUser(Barbershop barbershop, User user) {
    this.barbershop = barbershop;
    this.user = user;
  }

}
