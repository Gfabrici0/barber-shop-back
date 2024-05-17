package com.br.barbershop.model.entity;

import com.br.barbershop.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Table(name = "role")
@EqualsAndHashCode(of = "id")
public class Role {

  @Id
  @UuidGenerator
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private RoleEnum role;

  public Role(RoleEnum role) {
    this.role = role;
  }

  public void updateRole(RoleEnum role) {
    if(role != null) {
      this.role = role;
    }
  }

}
