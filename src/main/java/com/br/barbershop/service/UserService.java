package com.br.barbershop.service;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.enumeration.StatusEnum;
import com.br.barbershop.exception.UserNotFoundException;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.DTO.user.DataRegisterUser;
import com.br.barbershop.model.DTO.user.DataUpdateUser;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.model.entity.Status;
import com.br.barbershop.model.entity.User;
import com.br.barbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private StatusService statusService;

  public User registerUser(DataRegisterUser dataRegisterUser) {
    Role role = roleService.findByRole(dataRegisterUser.role() != null ? dataRegisterUser.role() : RoleEnum.ROLE_USER);
    Status status = statusService.findByStatus(StatusEnum.ACTIVE);
    return userRepository.save(new User(dataRegisterUser, role, status));
  }

  public Page<DataUser> getAllActiveUsers(Pageable pageable) {
    return userRepository.findAllActiveUsers(StatusEnum.ACTIVE.getStatus(), pageable)
      .map(DataUser::new);
  }

  public DataUser getActiveUserById(UUID id) {
    return userRepository.findActiveUserById(id).map(DataUser::new)
      .orElseThrow(() -> new UserNotFoundException("User not found"));
  }

  public User getActiveUserEntityById(UUID uuid) {
    return userRepository.findActiveUserById(uuid)
      .orElseThrow(() -> new UserNotFoundException("User not found"));
  }

  public DataUser getUserByEmail(String email) {
    return userRepository.findByEmail(email).map(DataUser::new)
      .orElseThrow(() -> new UserNotFoundException("User not found"));
  }

  public void updateUser(UUID id, DataUpdateUser dataUpdateUser) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    user.updateUser(dataUpdateUser);

    userRepository.save(user);
  }

  public void deleteUser(UUID userId) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new UserNotFoundException("User not found"));

    userRepository.deleteById(user.getId());
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("User not found with e-mail: " + email));

    return new org.springframework.security.core.userdetails.User(
      user.getEmail(),
      user.getPassword(),
      getAuthorities(user)
    );
  }

  private Collection<? extends GrantedAuthority> getAuthorities(User user) {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
  }

}
