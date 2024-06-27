package com.br.barbershop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
@EqualsAndHashCode(of = "id")
public class UserRole implements GrantedAuthority {

  @Id
  @UuidGenerator
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  UserRole(User user, Role role) {
    this.user = user;
    this.role = role;
  }

  @Override
  public String getAuthority() {
    return role.getRole();
  }

}
