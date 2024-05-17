package com.br.barbershop.service;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.exception.BarbershopNotFoundException;
import com.br.barbershop.model.DTO.barbershop.DataBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataRegisterBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataUpdateBarbershop;
import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.repository.BarbershopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BarbershopService {

  @Autowired
  private RoleService roleService;

  @Autowired
  private BarbershopRepository barbershopRepository;

  public Barbershop registerBarbershop(DataRegisterBarbershop dataRegisterBarbershop) {
    Role role = roleService.findByRole(RoleEnum.BARBERSHOP.getRole());
    return barbershopRepository.save(new Barbershop(dataRegisterBarbershop, role));
  }

  public Page<DataBarbershop> getAllBarbershops(Pageable pageable) {
    return barbershopRepository.findAll(pageable).map(DataBarbershop::new);
  }

  public DataBarbershop getBarbershopById(UUID id) {
    return barbershopRepository.findById(id).map(DataBarbershop::new)
        .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));
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
