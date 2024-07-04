package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Barber;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarberRepository extends JpaRepository<Barber, UUID> {
  @Query("SELECT b FROM Barber b WHERE b.user.email = :email")
  Optional<Barber> findBarberByUserEmail(@Param("email") String email);
  @Query("SELECT b FROM Barber b WHERE b.user.id = :id")
  Optional<Barber> findBarberByUserId(@Param("id") UUID id);
}
