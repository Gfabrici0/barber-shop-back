package com.br.barbershop.controller;

import com.br.barbershop.model.DTO.scheduling.AllDataScheduling;
import com.br.barbershop.model.DTO.scheduling.DataScheduling;
import com.br.barbershop.model.DTO.scheduling.DataUpdateScheduling;
import com.br.barbershop.model.DTO.scheduling.DateRegisterScheduling;
import com.br.barbershop.service.SchedulingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("scheduling")
@Controller
public class SchedulingController {

  @Autowired
  private SchedulingService schedulingService;

  @PostMapping
  public ResponseEntity<DataScheduling> registerScheduling(@RequestBody @Valid DateRegisterScheduling dateRegisterScheduling) {
    DataScheduling dataScheduling = schedulingService.registerScheduling(dateRegisterScheduling);
    return ResponseEntity.ok().body(dataScheduling);
  }

  @GetMapping
  public ResponseEntity<Page<AllDataScheduling>> getAllScheduling(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
    Page<AllDataScheduling> dataScheduling = schedulingService.getAllScheduling(pageable);
    return ResponseEntity.ok().body(dataScheduling);
  }

  @GetMapping("barbershop/{id}")
  public ResponseEntity<Page<DataScheduling>> getSchedulingByBarbershop(@PathVariable("id") UUID barbershopId, @PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
    Page<DataScheduling> dataScheduling = schedulingService.getSchedulingByBarbershop(barbershopId, pageable);
    return ResponseEntity.ok().body(dataScheduling);
  }

  @GetMapping("barber/{id}")
  public ResponseEntity<Page<DataScheduling>> getSchedulingByBarber(@PathVariable("id") UUID barberId, @PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
    Page<DataScheduling> dataScheduling = schedulingService.getSchedulingByBarber(barberId, pageable);
    return ResponseEntity.ok().body(dataScheduling);
  }

  @GetMapping("user/{id}")
  public ResponseEntity<Page<DataScheduling>> getSchedulingByUser(@PathVariable("id") UUID userId, @PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
    Page<DataScheduling> dataScheduling = schedulingService.getSchedulingByUser(userId, pageable);
    return ResponseEntity.ok().body(dataScheduling);
  }

  @GetMapping("{id}")
  public ResponseEntity<DataScheduling> getSchedulingById(@PathVariable("id") UUID schedulingId) {
    DataScheduling dataScheduling = schedulingService.getSchedulingById(schedulingId);
    return ResponseEntity.ok().body(dataScheduling);
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> updateScheduling(@PathVariable("id") UUID schedulingId, @RequestBody @Valid DataUpdateScheduling dataUpdateScheduling) {
    schedulingService.updateScheduling(schedulingId, dataUpdateScheduling);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteScheduling(@PathVariable("id") UUID schedulingId) {
    schedulingService.deleteScheduling(schedulingId);
    return ResponseEntity.noContent().build();
  }

}
