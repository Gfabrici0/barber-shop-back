package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Barbershop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarbershopRepository extends JpaRepository<Barbershop, UUID> {

  @Query("SELECT b FROM Barbershop b JOIN b.statusId s WHERE s.status = :status")
  Page<Barbershop> findAllByStatusId(@Param("status") String status, Pageable pageable);

  @Query("SELECT b FROM Barbershop b JOIN b.statusId s WHERE b.id = :id AND s.status = :status")
  Optional<Barbershop> findByIdAndStatusId(@Param("id") UUID id, @Param("status") String status);

  @Query("SELECT b FROM Barbershop b WHERE b.tradeName LIKE :tradeName")
  List<Barbershop> findByTradeName(@Param("tradeName") String tradeName);

  Optional<Barbershop> findByDocument(String document);

}