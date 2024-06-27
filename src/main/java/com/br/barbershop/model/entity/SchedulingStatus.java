package com.br.barbershop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scheduling_status")
@EqualsAndHashCode(of = "id")
public class SchedulingStatus {

  @Id
  @UuidGenerator
  private UUID id;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "description", nullable = false)
  private String description;

}
