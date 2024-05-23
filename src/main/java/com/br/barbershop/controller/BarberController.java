package com.br.barbershop.controller;

import com.br.barbershop.model.DTO.barber.DataBarber;
import com.br.barbershop.model.DTO.barber.DataRegisterBarber;
import com.br.barbershop.model.DTO.barber.DataUpdateBarber;
import com.br.barbershop.model.entity.Barber;
import com.br.barbershop.service.BarberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("barber")
@Controller
public class BarberController {

  @Autowired
  private BarberService barberService;

  @Transactional
  @PostMapping
  public ResponseEntity<DataBarber> registerBarber(@RequestBody @Valid DataRegisterBarber barberRegisterDto) {
    Barber createdBarber = barberService.registerBarber(barberRegisterDto);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(createdBarber.getId())
      .toUri();

    return ResponseEntity.created(location).body(new DataBarber(createdBarber));
  }

  @GetMapping
  public ResponseEntity<Page<DataBarber>> getAllBarber(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
    Page<DataBarber> result = barberService.getAllBarbers(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataBarber> getBarberById(@PathVariable UUID id) {
    DataBarber result = barberService.getBarberById(id);
    return ResponseEntity.ok().body(result);
  }

  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<String> updateBarber(@PathVariable UUID id, @RequestBody @Valid DataUpdateBarber dataUpdateBarber) {
    barberService.updateBarber(id, dataUpdateBarber);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBarberById(@PathVariable UUID id) {
    barberService.deleteBarber(id);
    return ResponseEntity.noContent().build();
  }

}
