package com.br.barbershop.service;

import com.br.barbershop.exception.UserNotFoundException;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.DTO.user.DataRegisterUser;
import com.br.barbershop.model.DTO.user.DataUpdateUser;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.model.entity.User;
import com.br.barbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;

  public User registerUser(DataRegisterUser dataRegisterUser) {
    Role role = roleService.findByRole(dataRegisterUser.role());
    return userRepository.save(new User(dataRegisterUser, role));
  }

  public Page<DataUser> getAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable).map(DataUser::new);
  }

  public DataUser getUserById(UUID id) {
    return userRepository.findById(id).map(DataUser::new)
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
}
