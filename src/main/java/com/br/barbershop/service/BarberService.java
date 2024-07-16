package com.br.barbershop.service;

import com.br.barbershop.enumeration.RoleEnum;
import com.br.barbershop.enumeration.StatusEnum;
import com.br.barbershop.exception.BarberNotFoundException;
import com.br.barbershop.exception.ServiceNotFoundException;
import com.br.barbershop.model.DTO.barber.BarberAvailability;
import com.br.barbershop.model.DTO.barber.BarberAvailabilityReport;
import com.br.barbershop.model.DTO.barber.DataBarber;
import com.br.barbershop.model.DTO.barber.DataRegisterBarber;
import com.br.barbershop.model.DTO.scheduling.DataScheduling;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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

  @Autowired
  private SchedulingService schedulingService;

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

  public Page<DataBarber> getBarbersByBarbershop(UUID barberShopId, Pageable pageable) {
    return barberRepository.findBarbersByBarberShop(barberShopId, pageable).map(DataBarber::new);
  }

  public void deleteBarberService(UUID id) {
    serviceRepository.deleteById(id);
  }

  public com.br.barbershop.model.entity.Service getServiceByServiceId(UUID uuid) {
    return serviceRepository.findById(uuid)
      .orElseThrow(() -> new ServiceNotFoundException("Service not found"));
  }

  public BarberAvailabilityReport getBarberAvailabilities(UUID barberId, LocalDate startDate, LocalDate endDate) {
    var schedulings = schedulingService.getSchedulingByBarber(barberId, startDate, endDate);

    var differenceInDays = ChronoUnit.DAYS.between(startDate, endDate);

    var daysDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    var dayHourDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    List<BarberAvailability> availabilities = new ArrayList<>();
    for (int i = 0; i < differenceInDays; i++) {
      var date = startDate.plusDays(i).format(daysDateFormatter);
      List<String> intervals = new ArrayList<>();
      LocalDateTime startDateTime = LocalDateTime.parse(date + "T" + String.format("%02d:00", 9));
      LocalDateTime endDateTime = LocalDateTime.parse(date + "T" + String.format("%02d:00", 18));

      while (startDateTime.isBefore(endDateTime)) {
        intervals.add(startDateTime.format(dayHourDateFormatter));
        startDateTime = startDateTime.plusMinutes(30);
      }
      availabilities.add(new BarberAvailability(date, intervals));
    }

    for (BarberAvailability availability : availabilities) {
      List<String> availableHours = new ArrayList<>(availability.hours());
      for (DataScheduling scheduling : schedulings) {
        var date = scheduling.date().format(dayHourDateFormatter);

        availableHours.removeIf(
          hour -> date.equals(hour)
        );
      }
      availability.hours().clear();
      availability.hours().addAll(availableHours);
    }

    return new BarberAvailabilityReport(barberId, availabilities);
  }
}
