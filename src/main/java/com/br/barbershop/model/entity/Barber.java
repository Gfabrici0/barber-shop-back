package com.br.barbershop.model.entity;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.model.DTO.barber.DataRegisterBarber;
import com.br.barbershop.model.DTO.user.DataRegisterUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "barber")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barber {

  @Id
  @UuidGenerator
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", nullable = false)
  public User user;

  @ManyToOne
  @JoinColumn(name = "barbershop_id", nullable = false)
  public Barbershop barbershop;

  public Barber(DataRegisterBarber dataRegisterBarber, Role role, Barbershop barbershop) {
    this.user = addUser(dataRegisterBarber, role);
    this.barbershop = barbershop;
  }

  public User addUser(DataRegisterBarber dataRegisterBarber, Role role) {
    DataRegisterUser dataRegisterUser = new DataRegisterUser(
      dataRegisterBarber.email(),
      dataRegisterBarber.username(),
      dataRegisterBarber.password(),
      dataRegisterBarber.document(),
      RoleEnum.BARBER,
      dataRegisterBarber.phoneNumber(),
      dataRegisterBarber.dateOfBirth(),
      dataRegisterBarber.address()
    );

    return new User(dataRegisterUser, role);
  }

}
