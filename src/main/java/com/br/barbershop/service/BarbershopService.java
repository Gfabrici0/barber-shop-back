package com.br.barbershop.service;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.exception.BarbershopNotFoundException;
import com.br.barbershop.model.DTO.barber.BarberWithoutUser;
import com.br.barbershop.model.DTO.barber.BarbershopWithBarbers;
import com.br.barbershop.model.DTO.barbershop.DataBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataRegisterBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataUpdateBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataBarbershopWithoudUser;
import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.repository.BarbershopRepository;
import com.br.barbershop.repository.CustomBarbershopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BarbershopService {

  @Autowired
  private RoleService roleService;

  @Autowired
  private BarbershopRepository barbershopRepository;

  @Autowired
  private CustomBarbershopRepository customBarbershopRepository;

  public Barbershop registerBarbershop(DataRegisterBarbershop dataRegisterBarbershop) {
    Role role = roleService.findByRole(RoleEnum.BARBERSHOP);
    return barbershopRepository.save(new Barbershop(dataRegisterBarbershop, role));
  }

  public Page<DataBarbershopWithoudUser> getAllBarbershops(Pageable pageable) {
    return barbershopRepository.findAll(pageable).map(DataBarbershopWithoudUser::new);
  }

  public DataBarbershop getDataBarbershopById(UUID id) {
    return barbershopRepository.findById(id).map(DataBarbershop::new)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));
  }

  public Barbershop getBarbershopById(UUID id) {
    return barbershopRepository.findById(id)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));
  }

  public BarbershopWithBarbers getBarbersFromBarbershop(UUID id) {
    Barbershop barbershop = barbershopRepository.findById(id)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));

    List<BarberWithoutUser> barbers = customBarbershopRepository.findBarbersFromBarbershopById(id);

    return new BarbershopWithBarbers(barbershop, barbers);
  }

  public void updateBarbershop(UUID id, DataUpdateBarbershop dataUpdateBarbershop) {
    Barbershop barbershop = barbershopRepository.findById(id)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));

    barbershop.updateBarberShop(dataUpdateBarbershop);

    barbershopRepository.save(barbershop);
  }

  public void deleteBarbershop(UUID id) {
    Barbershop barbershop = barbershopRepository.findById(id)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));

    barbershopRepository.deleteById(barbershop.getId());
  }

}
