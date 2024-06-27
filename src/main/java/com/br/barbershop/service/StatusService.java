package com.br.barbershop.service;

import com.br.barbershop.enumeration.StatusEnum;
import com.br.barbershop.exception.StatusNotFoundException;
import com.br.barbershop.model.entity.Status;
import com.br.barbershop.repository.BarbershopStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

  @Autowired
  private BarbershopStatusRepository barbershopStatusRepository;

  public Status findByStatus(StatusEnum status) {
    return barbershopStatusRepository.findByStatus(status.getStatus())
      .orElseThrow(() -> new StatusNotFoundException("Status not found"));
  }

}
