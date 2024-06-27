package com.br.barbershop.service;

import com.br.barbershop.enumeration.StatusEnum;
import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.exception.BarbershopNotFoundException;
import com.br.barbershop.exception.ServiceNotFoundException;
import com.br.barbershop.model.DTO.barber.BarberWithoutUser;
import com.br.barbershop.model.DTO.barber.BarbershopWithBarbers;
import com.br.barbershop.model.DTO.barbershop.DataBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataRegisterBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataUpdateBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataBarbershopWithoudUser;
import com.br.barbershop.model.DTO.service.DataBarbershopService;
import com.br.barbershop.model.DTO.service.DataRegisterBarbershopService;
import com.br.barbershop.model.DTO.service.DataUpdateBarbershopService;
import com.br.barbershop.model.DTO.service.ListBarbershopService;
import com.br.barbershop.model.entity.Barber;
import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.model.entity.Status;
import com.br.barbershop.model.entity.Role;
import com.br.barbershop.repository.BarbershopRepository;
import com.br.barbershop.repository.ServiceRepository;
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
  private BarberService barberService;

  @Autowired
  private StatusService statusService;

  @Autowired
  private ServiceRepository serviceRepository;

  @Autowired
  private BarbershopRepository barbershopRepository;

  public Barbershop registerBarbershop(DataRegisterBarbershop dataRegisterBarbershop) {
    Role role = roleService.findByRole(RoleEnum.ROLE_BARBERSHOP);
    Status status = statusService.findByStatus(StatusEnum.PENDING_APPROVAL);
    return barbershopRepository.save(new Barbershop(dataRegisterBarbershop, role, status));
  }

  public Page<DataBarbershopWithoudUser> getAllBarbershops(Pageable pageable) {
    return barbershopRepository.findAllByStatusId(StatusEnum.ACTIVE.getStatus(), pageable)
      .map(DataBarbershopWithoudUser::new);
  }

  public DataBarbershop getDataBarbershopById(UUID id) {
    return barbershopRepository.findByIdAndStatusId(id, StatusEnum.ACTIVE.getStatus())
      .map(DataBarbershop::new)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));
  }

  public Barbershop getBarbershopById(UUID id) {
    return barbershopRepository.findByIdAndStatusId(id, StatusEnum.ACTIVE.getStatus())
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));
  }

  public BarbershopWithBarbers getBarbersFromBarbershop(UUID id) {
    Barbershop barbershop = barbershopRepository.findById(id)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));

    List<BarberWithoutUser> barbers = barbershop.getBarbers().stream()
      .map(BarberWithoutUser::new)
      .toList();

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

  public DataBarbershopService registerService(DataRegisterBarbershopService dataRegisterBarbershopService) {
    Barbershop barbershop = barbershopRepository.findById(dataRegisterBarbershopService.barbershopId())
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));

    Barber barber = barberService.getBarberEntityById(dataRegisterBarbershopService.barberId());

    com.br.barbershop.model.entity.Service service = serviceRepository.save(new com.br.barbershop.model.entity.Service(dataRegisterBarbershopService, barbershop, barber));
    return new DataBarbershopService(barbershop, service);
  }

  public ListBarbershopService getAllBarbershopServices(UUID id) {
    Barbershop barbershop = barbershopRepository.findById(id)
      .orElseThrow(() -> new BarbershopNotFoundException("Barbershop not found"));

    List<com.br.barbershop.model.entity.Service> service = serviceRepository.findByBarbershopId(barbershop.getId());
    return new ListBarbershopService(barbershop, service);
  }

  public void updateBarbershopService(UUID id, DataUpdateBarbershopService dataUpdateBarbershopService) {
    com.br.barbershop.model.entity.Service service = serviceRepository.findById(id)
      .orElseThrow(() -> new ServiceNotFoundException("Service not found"));

    service.updateService(dataUpdateBarbershopService);

    serviceRepository.save(service);
  }

  public void deleteBarbershopServiceById(UUID id) {
    com.br.barbershop.model.entity.Service service = serviceRepository.findById(id)
      .orElseThrow(() -> new ServiceNotFoundException("Service not found"));

    serviceRepository.deleteById(service.getId());
  }

  public Page<DataBarbershop> getAllBarbershopsWithPendingStatusById(Pageable pageable) {
    return barbershopRepository.findAllByStatusId(StatusEnum.PENDING_APPROVAL.getStatus(), pageable)
      .map(DataBarbershop::new);
  }

}
