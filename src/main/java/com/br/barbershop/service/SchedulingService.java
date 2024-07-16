package com.br.barbershop.service;

import com.br.barbershop.enumeration.SchedulingStatusEnum;
import com.br.barbershop.model.DTO.scheduling.*;
import com.br.barbershop.model.entity.*;
import com.br.barbershop.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchedulingService {

  @Autowired
  private SchedulingRepository schedulingRepository;

  @Autowired
  private BarberService barberService;

  @Autowired
  private UserService userService;

  @Autowired
  private BarbershopService barbershopService;

  @Autowired
  private SchedulingStatusService schedulingStatusService;

  public DataScheduling registerScheduling(DateRegisterScheduling dateRegisterScheduling) {
    Barber barber = barberService.getBarberEntityById(dateRegisterScheduling.barberId());
    User user = userService.getActiveUserEntityById(dateRegisterScheduling.userId());
    Barbershop barbershop = barbershopService.getBarbershopById(dateRegisterScheduling.barbershopId());
    com.br.barbershop.model.entity.Service service = barberService.getServiceByBarberId(dateRegisterScheduling.barberId());
    SchedulingStatus status = schedulingStatusService.getSchedulingStatusByStatus(SchedulingStatusEnum.PENDING_BARBER_APPROVAL);
    Scheduling scheduling = schedulingRepository.save(new Scheduling(dateRegisterScheduling, status, user, barbershop, service, barber));
    return new DataScheduling(scheduling);
  }


  public Page<AllDataScheduling> getAllScheduling(Pageable pageable) {
    return schedulingRepository.findAll(pageable).map(AllDataScheduling::new);
  }

  public Page<DataScheduling> getSchedulingByBarbershop(UUID barbershopId, Pageable pageable) {
    return schedulingRepository.findByBarbershopId(barbershopId, pageable).map(DataScheduling::new);
  }

  public Page<DataScheduling> getSchedulingByBarber(UUID barberId, Pageable pageable) {
    return schedulingRepository.findByBarberId(barberId, pageable).map(DataScheduling::new);
  }

  public Page<DataScheduling> getPendingSchedulingByUserBarberId(UUID userBarberId, Pageable pageable) {
    SchedulingStatus scheduling = schedulingStatusService.getSchedulingStatusByStatus(SchedulingStatusEnum.PENDING_BARBER_APPROVAL);
    return schedulingRepository.findByUserBarberIdWithPendingStatus(userBarberId, scheduling.getId(), pageable).map(DataScheduling::new);
  }

  public Page<DataScheduling> getPendingSchedulingByUserBarbershopId(UUID userBarbershopId, Pageable pageable) {
    SchedulingStatus scheduling = schedulingStatusService.getSchedulingStatusByStatus(SchedulingStatusEnum.PENDING_BARBER_APPROVAL);
    return schedulingRepository.findByUserBarbershopIdWithPendingStatus(userBarbershopId, scheduling.getId(), pageable).map(DataScheduling::new);
  }

  public Page<DataScheduling> getSchedulingByUser(UUID userId, Pageable pageable) {
    var barber = barberService.getBarberByUserId(userId);
    return barber.map(value -> schedulingRepository.findByBarberId(value.getId(), pageable).map(DataScheduling::new))
        .orElseGet(() -> schedulingRepository.findByUserId(userId, pageable).map(DataScheduling::new));
  }

  public DataScheduling getSchedulingById(UUID id) {
    return schedulingRepository.findById(id).map(DataScheduling::new)
      .orElseThrow(() -> new RuntimeException("Scheduling not found"));
  }

  public void updateScheduling(UUID schedulingId, DataUpdateScheduling dataUpdateScheduling) {
    Scheduling scheduling = schedulingRepository.findById(schedulingId)
      .orElseThrow(() -> new RuntimeException("Scheduling not found"));
    Barber newBarber = barberService.getBarberEntityById(dataUpdateScheduling.barberId());
    com.br.barbershop.model.entity.Service newService = barberService.getServiceByBarberId(dataUpdateScheduling.barberId());
    SchedulingStatus status = schedulingStatusService.getSchedulingStatusByStatus(dataUpdateScheduling.status());
    scheduling.updateScheduling(dataUpdateScheduling, status, newBarber, newService);
    schedulingRepository.save(scheduling);
  }

  public void deleteScheduling(UUID id) {
    schedulingRepository.deleteById(id);
  }

  public void updateSchedulingStatus(UUID schedulingId, DataUpdateStatusScheduling dataUpdateStatusScheduling) {
    System.out.println(dataUpdateStatusScheduling.status());
    Scheduling scheduling = schedulingRepository.findById(schedulingId)
      .orElseThrow(() -> new RuntimeException("Scheduling not found"));
    SchedulingStatus status = schedulingStatusService.getSchedulingStatusByStatus(dataUpdateStatusScheduling.status());
    scheduling.setStatus(status);
    schedulingRepository.save(scheduling);
  }

}
