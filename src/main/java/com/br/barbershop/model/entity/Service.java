package com.br.barbershop.model.entity;

import com.br.barbershop.model.DTO.service.DataRegisterBarbershopService;
import com.br.barbershop.model.DTO.service.DataUpdateBarbershopService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Service {

  @Id
  @UuidGenerator
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "barbershop_id", nullable = false)
  private Barbershop barbershop;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "barber_id", nullable = false)
  private Barber barber;

  @Column(name = "service_name", nullable = false)
  private String serviceName;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "value", nullable = false)
  private BigDecimal value;

  public Service(DataRegisterBarbershopService dataRegisterBarbershopService, Barbershop barbershop, Barber barber) {
    this.barbershop = barbershop;
    this.barber = barber;
    this.serviceName = dataRegisterBarbershopService.serviceName();
    this.description = dataRegisterBarbershopService.description();
    this.value = dataRegisterBarbershopService.value();
  }

  public void updateService(DataUpdateBarbershopService dataUpdateBarbershopService) {
    if (dataUpdateBarbershopService.serviceName() != null && !dataUpdateBarbershopService.serviceName().isEmpty()) {
      this.serviceName = dataUpdateBarbershopService.serviceName();
    }
    if (dataUpdateBarbershopService.description() != null && !dataUpdateBarbershopService.description().isEmpty()) {
      this.description = dataUpdateBarbershopService.description();
    }
    if (dataUpdateBarbershopService.value() != null) {
      this.value = dataUpdateBarbershopService.value();
    }
  }

}
