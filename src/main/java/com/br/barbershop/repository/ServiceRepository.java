package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {

  List<Service> findByBarbershopId(UUID id);

}
