package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Barbershop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BarbershopRepository extends JpaRepository<Barbershop, UUID> {
}
