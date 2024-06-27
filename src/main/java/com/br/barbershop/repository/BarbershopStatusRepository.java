package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BarbershopStatusRepository extends JpaRepository<Status, UUID> {

  Optional<Status> findByStatus(String status);

}
