package com.br.barbershop.model.entity;

import com.br.barbershop.model.DTO.DataRegisterUser;
import com.br.barbershop.model.DTO.DataUpdateUser;
import jakarta.persistence.*;

import java.util.*;

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

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "document", unique = true, nullable = false)
  private String document;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "date_of_birth", nullable = false)
  private Date dateOfBirth;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserAddress> userAddresses = new ArrayList<>();

  public User(DataRegisterUser dataRegisterUser) {
    email = dataRegisterUser.email();
    username = dataRegisterUser.username();
    password = new BCryptPasswordEncoder().encode(dataRegisterUser.password());
    document = dataRegisterUser.document();
    phoneNumber = dataRegisterUser.phoneNumber();
    dateOfBirth = dataRegisterUser.dateOfBirth();
    addAddress(new Address(dataRegisterUser.dataAdress()));
  }

  public void updateUser(DataUpdateUser dataUpdateUser) {
    if(dataUpdateUser.dateOfBirth() != null) {
      this.dateOfBirth = dataUpdateUser.dateOfBirth();
    }
  }

  public void addAddress(Address address) {
    UserAddress userAddress = new UserAddress();
    userAddress.setUser(this);
    userAddress.setAddress(address);
    this.userAddresses.add(userAddress);
  }

  /* Overrides the getUsername method of the UserDetails class to use email for authentication */
  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}