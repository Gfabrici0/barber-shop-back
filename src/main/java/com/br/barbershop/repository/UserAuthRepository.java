package com.br.barbershop.repository;

import com.br.barbershop.model.entity.User;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<User, String> {

  Optional<User> findByUsername(String username);

  Optional<User> findById(UUID id);

  void deleteById(UUID id);

  Optional<User> findByEmail(String email);
}
