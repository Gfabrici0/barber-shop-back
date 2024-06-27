package com.br.barbershop.repository;

import com.br.barbershop.model.entity.SchedulingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchedulingStatusRepository extends JpaRepository<SchedulingStatus, UUID> {
  Optional<SchedulingStatus> getByStatus(String status);
}
