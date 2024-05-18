package com.br.barbershop.model.entity;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.model.DTO.address.DataRegisterAddress;
import com.br.barbershop.model.DTO.barbershop.DataRegisterBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataUpdateBarbershop;
import com.br.barbershop.model.DTO.user.DataRegisterUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "barbershop")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barbershop {

  @Id
  @UuidGenerator
  private UUID id;

  @Column(name = "ownerName", nullable = false)
  private String ownerName;

  @Column(name = "tradeName", nullable = false)
  private String tradeName;

  @Column(name = "corporateName", nullable = false)
  private String corporateName;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "document", unique = true, nullable = false)
  private String document;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime created_at;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updated_at;

  @OneToMany(mappedBy = "barbershop", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BarbershopAddress> barbershopAddresses = new ArrayList<>();

  @OneToMany(mappedBy = "barbershop", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BarbershopUser> barbershopUsers = new ArrayList<>();

  public Barbershop(DataRegisterBarbershop dataRegisterBarbershop, Role role) {
    LocalDateTime localDateTime = LocalDateTime.now();

    this.ownerName = dataRegisterBarbershop.ownerName();
    this.tradeName = dataRegisterBarbershop.tradeName();
    this.corporateName = dataRegisterBarbershop.corporateName();
    this.email = dataRegisterBarbershop.email();
    this.document = dataRegisterBarbershop.document();
    this.phoneNumber = dataRegisterBarbershop.phoneNumber();
    this.created_at = localDateTime;
    this.updated_at = localDateTime;

    addAddress(dataRegisterBarbershop.barbershopAddress());
    addUser(dataRegisterBarbershop, role);
  }

  public void updateBarberShop(DataUpdateBarbershop dataUpdateBarbershop) {

  }

  private void addAddress(DataRegisterAddress dataRegisterAddress) {
    this.barbershopAddresses.add(new BarbershopAddress(this, new Address(dataRegisterAddress)));
  }

  private void addUser(DataRegisterBarbershop dataRegisterBarbershop, Role role) {
    DataRegisterUser dataRegisterUser = new DataRegisterUser(
      dataRegisterBarbershop.email(),
      dataRegisterBarbershop.ownerName(),
      dataRegisterBarbershop.password(),
      dataRegisterBarbershop.document(),
      RoleEnum.BARBERSHOP,
      dataRegisterBarbershop.phoneNumber(),
      dataRegisterBarbershop.dateOfBirth(),
      dataRegisterBarbershop.personalAddress()
    );

    this.barbershopUsers.add(new BarbershopUser(this, new User(dataRegisterUser, role)));
  }

}
