package com.br.barbershop.repository;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

  @Query(value = "SELECT * FROM role r WHERE r.role = CAST(:role AS role_type)", nativeQuery = true)
  Optional<Role> findByRole(@Param("role") String role);

}
