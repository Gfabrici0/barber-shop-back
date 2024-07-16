package com.br.barbershop.repository;

import com.br.barbershop.model.entity.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

  Page<Scheduling> findByBarbershopId(UUID barbershopId, Pageable pageable);

  Page<Scheduling> findByBarberId(UUID barberId, Pageable pageable);

  Page<Scheduling> findByUserId(UUID userId, Pageable pageable);

  @Query("SELECT s FROM Scheduling s JOIN s.barber b JOIN b.user u WHERE u.id = :userId AND s.status.id = :statusId")
  Page<Scheduling> findByUserBarberIdWithPendingStatus(@Param("userId") UUID userId, @Param("statusId") UUID statusId, Pageable pageable);

  @Query("SELECT s FROM Scheduling s JOIN s.barbershop bs JOIN bs.barbershopUsers bu JOIN bu.user u WHERE u.id = :userId AND s.status.id = :statusId")
  Page<Scheduling> findByUserBarbershopIdWithPendingStatus(@Param("userId") UUID userId, @Param("statusId") UUID statusId, Pageable pageable);

  @Query("SELECT s FROM Scheduling s JOIN s.barber b WHERE b.id = :barberId AND s.date >= :startDate AND s.date <= :endDate")
  List<Scheduling> findByBarberIdAndDate(@Param("barberId") UUID barberId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
