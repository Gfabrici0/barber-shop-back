package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

  @Query("SELECT r FROM Role r WHERE r.role = :role")
  Optional<Role> findByRole(@Param("role") String role);

}
