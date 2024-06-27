package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {

  List<Service> findByBarbershopId(UUID id);

  Optional<Service> findByBarberId(UUID barbaershopId);

  Page<Service> findByBarberId(UUID id, Pageable pageable);
}
