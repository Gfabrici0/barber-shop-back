package com.br.barbershop.controller;

import com.br.barbershop.exception.BarberNotFoundException;
import com.br.barbershop.model.DTO.barber.DataBarber;
import com.br.barbershop.model.DTO.barber.DataRegisterBarber;
import com.br.barbershop.model.DTO.service.DataService;
import com.br.barbershop.model.entity.Barber;
import com.br.barbershop.service.BarberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("barber")
@Controller
public class BarberController {

  @Autowired
  private BarberService barberService;

  @Transactional
  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP')")
  public ResponseEntity<DataBarber> registerBarber(@RequestBody @Valid DataRegisterBarber barberRegisterDto) {
    Barber createdBarber = barberService.registerBarber(barberRegisterDto);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(createdBarber.getId())
      .toUri();

    return ResponseEntity.created(location).body(new DataBarber(createdBarber));
  }

  @GetMapping("barbershop/{id}")
  public ResponseEntity<Page<DataBarber>> getAllBarbersByShop(@PathVariable UUID id, @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
    var result = barberService.getBarbersByBarbershop(id, pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("service/barber/{id}")
  public ResponseEntity<Page<DataService>> getServiceByBarber(@PathVariable UUID id, @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
    Page<DataService> result = barberService.getServicesByBarber(id, pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("email/{email}")
  public ResponseEntity<DataBarber> getBarberByEmail(@PathVariable String email) {
    DataBarber result = barberService.getBarberByEmail(email);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Page<DataBarber>> getAllBarber(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
    Page<DataBarber> result = barberService.getAllBarbers(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP', 'USER')")
  public ResponseEntity<DataBarber> getBarberById(@PathVariable UUID id) {
    DataBarber result = barberService.getBarberById(id);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("user/{userId}")
  public ResponseEntity<DataBarber> getBarberByUserId(@PathVariable UUID userId) {
    DataBarber result = barberService.getBarberByUserId(userId).map(DataBarber::new)
      .orElseThrow(() -> new BarberNotFoundException("Barber not found"));
    return ResponseEntity.ok().body(result);
  }

  @Transactional
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP')")
  public ResponseEntity<?> deleteBarberById(@PathVariable UUID id) {
    barberService.deleteBarber(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/availability")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP', 'USER')")
  public ResponseEntity<?> getBarberAvailabilities(@PathVariable UUID id) {
    var date = LocalDate.now();
    var futureDate = LocalDate.now().plusDays(7);
    var barberAvailabilities = barberService.getBarberAvailabilities(
            id, date, futureDate
    );

    return ResponseEntity.ok().body(barberAvailabilities);
  }
}
