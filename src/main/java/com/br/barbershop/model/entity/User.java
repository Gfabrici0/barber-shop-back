package com.br.barbershop.model.entity;

import com.br.barbershop.model.DTO.DataRegisterClient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "date_of_birth")
  private String dateOfBirth;

  @Column(name = "address_street")
  private String addressStreet;

  @Column(name = "address_number")
  private String addressNumber;

  @Column(name = "address_city")
  private String addressCity;

  @Column(name = "document")
  private String document;

  public User(DataRegisterClient dataRegisterClient) {
    username = dataRegisterClient.username();
    password = new BCryptPasswordEncoder().encode(dataRegisterClient.password());
    document = dataRegisterClient.document();
    email = dataRegisterClient.email();
    dateOfBirth = dataRegisterClient.dateOfBirth();
    addressStreet = dataRegisterClient.addressStreet();
    addressNumber = dataRegisterClient.addressNumber();
    addressCity = dataRegisterClient.addressCity();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

}