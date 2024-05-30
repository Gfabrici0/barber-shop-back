package com.br.barbershop.service;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.exception.BarberNotFoundException;
import com.br.barbershop.model.DTO.barber.DataBarber;
import com.br.barbershop.model.DTO.barber.DataRegisterBarber;
import com.br.barbershop.model.entity.Barber;
import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BarberService {

  @Autowired
  private BarberRepository barberRepository;

  @Autowired
  private BarbershopService barbershopService;

  @Autowired
  private RoleService roleService;

  public Barber registerBarber(DataRegisterBarber barberRegisterDto) {
    Role role = roleService.findByRole(RoleEnum.BARBER);

    Barbershop barbershop = barbershopService.getBarbershopById(barberRegisterDto.barbershopId());

    return barberRepository.save(new Barber(barberRegisterDto, role, barbershop));
  }

  public Page<DataBarber> getAllBarbers(Pageable pageable) {
    return barberRepository.findAll(pageable).map(DataBarber::new);
  }

  public DataBarber getBarberById(UUID id) {
    return barberRepository.findById(id).map(DataBarber::new)
      .orElseThrow(() -> new BarberNotFoundException("Barber not found"));
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
}
