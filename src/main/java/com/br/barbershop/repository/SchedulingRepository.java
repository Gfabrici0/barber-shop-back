package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

  Page<Scheduling> findByBarbershopId(UUID barbershopId, Pageable pageable);

  Page<Scheduling> findByBarberId(UUID barberId, Pageable pageable);

  Page<Scheduling> findByUserId(UUID userId, Pageable pageable);
}
