package com.br.barbershop.service;

import com.br.barbershop.exception.UserNotFoundException;
import com.br.barbershop.model.DTO.DataUser;
import com.br.barbershop.model.DTO.DataRegisterUser;
import com.br.barbershop.model.DTO.DataUpdateUser;
import com.br.barbershop.model.entity.User;
import com.br.barbershop.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthService {

  @Autowired
  private UserAuthRepository userAuthRepository;

  public User registerUser(DataRegisterUser dataRegisterUser) {
    return userAuthRepository.save(new User(dataRegisterUser));
  }

  public Page<DataUser> getAllUsers(Pageable pageable) {
    return userAuthRepository.findAll(pageable).map(DataUser::new);
  }

  public DataUser getUserById(UUID id) {
    return userAuthRepository.findById(id).map(DataUser::new)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
  }

  public void deleteUser(UUID userId) {
    User user = userAuthRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    userAuthRepository.deleteById(user.getId());
  }

  public void updateUser(UUID id, DataUpdateUser dataUpdateUser) {
    User user = userAuthRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    user.updateUser(dataUpdateUser);
    userAuthRepository.save(user);
  }
}
