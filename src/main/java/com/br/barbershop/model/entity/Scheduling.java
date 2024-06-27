package com.br.barbershop.model.entity;

import com.br.barbershop.model.DTO.scheduling.DataUpdateScheduling;
import com.br.barbershop.model.DTO.scheduling.DateRegisterScheduling;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scheduling")
@EqualsAndHashCode(of = "id")
public class Scheduling {

  @Id
  @UuidGenerator
  private UUID id;

  @Column(name = "date", nullable = false)
  private LocalDateTime date;

  @ManyToOne
  @JoinColumn(name = "scheduling_status_id", nullable = false)
  private SchedulingStatus status;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @ManyToOne
  @JoinColumn(name = "barbershop_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Barbershop barbershop;

  @ManyToOne
  @JoinColumn(name = "service_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Service service;

  @ManyToOne
  @JoinColumn(name = "barber_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Barber barber;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public Scheduling(DateRegisterScheduling dateRegisterScheduling, SchedulingStatus status, User user, Barbershop barbershop, Service service, Barber barber) {
    LocalDateTime dateTime = LocalDateTime.now();
    this.date = dateRegisterScheduling.date();
    this.status = status;
    this.user = user;
    this.barbershop = barbershop;
    this.service = service;
    this.barber = barber;
    this.createdAt = dateTime;
    this.updatedAt = dateTime;
  }

  public void updateScheduling(DataUpdateScheduling dataUpdateScheduling, SchedulingStatus status, Barber newBarber, Service newService) {
    this.status = status != null ? status : this.status;
    this.barber = newBarber != null ? newBarber : this.barber;
    this.service = newService != null ? newService : this.service;
    this.date = dataUpdateScheduling.date() != null ? dataUpdateScheduling.date() : this.date;
    this.updatedAt = LocalDateTime.now();
  }

}
