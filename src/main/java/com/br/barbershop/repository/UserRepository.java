package com.br.barbershop.repository;

import com.br.barbershop.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  @Query("SELECT u FROM User u JOIN u.statusId s WHERE s.status = :status")
  Page<User> findAllActiveUsers(@Param("status") String status, Pageable pageable);

  Optional<User> findActiveUserById(UUID id);

}
