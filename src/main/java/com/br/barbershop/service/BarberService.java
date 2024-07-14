package com.br.barbershop.service;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.enumeration.StatusEnum;
import com.br.barbershop.exception.BarberNotFoundException;
import com.br.barbershop.exception.ServiceNotFoundException;
import com.br.barbershop.model.DTO.barber.DataBarber;
import com.br.barbershop.model.DTO.barber.DataRegisterBarber;
import com.br.barbershop.model.DTO.service.DataService;
import com.br.barbershop.model.entity.Barber;
import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.model.entity.Status;
import com.br.barbershop.repository.BarberRepository;
import com.br.barbershop.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BarberService {

  @Autowired
  private BarberRepository barberRepository;

  @Autowired
  private BarbershopService barbershopService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private StatusService statusService;

  @Autowired
  private ServiceRepository serviceRepository;

  public Barber registerBarber(DataRegisterBarber barberRegisterDto) {
    Role role = roleService.findByRole(RoleEnum.ROLE_BARBER);

    Barbershop barbershop = barbershopService.getBarbershopById(barberRegisterDto.barbershopId());

    Status status = statusService.findByStatus(StatusEnum.ACTIVE);

    return barberRepository.save(new Barber(barberRegisterDto, role, barbershop, status));
  }

  public Optional<Barber> getBarberByUserId(UUID userId) {
    return barberRepository.findBarberByUserId(userId);
  }

  public Page<DataBarber> getAllBarbers(Pageable pageable) {
    return barberRepository.findAll(pageable).map(DataBarber::new);
  }

  public DataBarber getBarberById(UUID id) {
    return barberRepository.findById(id).map(DataBarber::new)
      .orElseThrow(() -> new BarberNotFoundException("Barber not found"));
  }

  public com.br.barbershop.model.entity.Service getServiceByBarberId(UUID barbaershopId) {
    return serviceRepository.findByBarberId(barbaershopId)
        .orElseThrow(() -> new ServiceNotFoundException("Service not found"));
  }

  public void deleteBarber(UUID id) {
    Barber barber = barberRepository.findById(id)
      .orElseThrow(() -> new BarberNotFoundException("Barber not found"));

    barberRepository.deleteById(barber.getId());
  }

  public Barber getBarberEntityById(UUID id) {
    return barberRepository.findById(id)
      .orElseThrow(() -> new BarberNotFoundException("Barber not found"));
  }

  public Page<DataService> getServicesByBarber(UUID id, Pageable pageable) {
    return serviceRepository.findByBarberId(id, pageable).map(DataService::new);
  }

  public DataBarber getBarberByEmail(String email) {
    return barberRepository.findBarberByUserEmail(email)
      .map(DataBarber::new)
      .orElseThrow(() -> new BarberNotFoundException("Barber not found"));
  }

  public void deleteBarberService(UUID id) {
    serviceRepository.deleteById(id);
  }
}
