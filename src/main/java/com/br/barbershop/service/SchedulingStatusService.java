package com.br.barbershop.service;

import com.br.barbershop.enumeration.SchedulingStatusEnum;
import com.br.barbershop.model.entity.SchedulingStatus;
import com.br.barbershop.repository.SchedulingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulingStatusService {

  @Autowired
  private SchedulingStatusRepository schedulingStatusRepository;

  public SchedulingStatus getSchedulingStatusByStatus(SchedulingStatusEnum status) {
    return schedulingStatusRepository.getByStatus(status.getStatus())
      .orElseThrow(() -> new RuntimeException("Scheduling status not found"));
  }

}
