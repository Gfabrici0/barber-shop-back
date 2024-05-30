package com.br.barbershop.controller;

import com.br.barbershop.model.DTO.barber.BarbershopWithBarbers;
import com.br.barbershop.model.DTO.barbershop.DataBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataRegisterBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataUpdateBarbershop;
import com.br.barbershop.model.DTO.barbershop.DataBarbershopWithoudUser;
import com.br.barbershop.model.DTO.service.DataBarbershopService;
import com.br.barbershop.model.DTO.service.DataRegisterBarbershopService;
import com.br.barbershop.model.DTO.service.DataUpdateBarbershopService;
import com.br.barbershop.model.DTO.service.ListBarbershopService;
import com.br.barbershop.model.entity.Barbershop;
import com.br.barbershop.service.BarbershopService;
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
@RequestMapping("barbershop")
@Controller
public class BarbershopController {

  @Autowired
  private BarbershopService barbershopService;

  @Transactional
  @PostMapping
  public ResponseEntity<DataBarbershop> registerBarbershop(@RequestBody @Valid DataRegisterBarbershop dataRegisterBarbershop) {
    Barbershop createdBarbershop = barbershopService.registerBarbershop(dataRegisterBarbershop);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(createdBarbershop.getId())
      .toUri();

    return ResponseEntity.created(location).body(new DataBarbershop(createdBarbershop));
  }

  @Transactional
  @PostMapping("service")
  public ResponseEntity<DataBarbershopService> registerService(@RequestBody @Valid DataRegisterBarbershopService dataRegisterBarbershopService) {
    DataBarbershopService service = barbershopService.registerService(dataRegisterBarbershopService);
    return ResponseEntity.ok().body(service);
  }

  @GetMapping("service/{id}")
  public ResponseEntity<ListBarbershopService> getAllBarbershopServices(@PathVariable UUID id) {
    ListBarbershopService services = barbershopService.getAllBarbershopServices(id);
    return ResponseEntity.ok().body(services);
  }

  @PutMapping("service/{id}")
  public ResponseEntity<?> updateBarbershopService(@PathVariable UUID id, @RequestBody DataUpdateBarbershopService dataUpdateBarbershopService) {
    barbershopService.updateBarbershopService(id, dataUpdateBarbershopService);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("service/{id}")
  public ResponseEntity<?> deleteBarbershopServiceById(@PathVariable UUID id) {
    barbershopService.deleteBarbershopServiceById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Page<DataBarbershopWithoudUser>> getAllBarbershop(@PageableDefault(size = 10, sort = {"document"}) Pageable pageable) {
    Page<DataBarbershopWithoudUser> result = barbershopService.getAllBarbershops(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataBarbershop> getBarbershopById(@PathVariable UUID id) {
    DataBarbershop result = barbershopService.getDataBarbershopById(id);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("barbers/{id}")
  public ResponseEntity<BarbershopWithBarbers> getBarbersFromBarbershop(@PathVariable UUID id) {
    BarbershopWithBarbers barbers = barbershopService.getBarbersFromBarbershop(id);
    return ResponseEntity.ok().body(barbers);
  }

  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<String> updateBarbershop(@PathVariable UUID id,@RequestBody @Valid DataUpdateBarbershop dataUpdateBarbershop) {
    barbershopService.updateBarbershop(id, dataUpdateBarbershop);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBarbershopById(@PathVariable UUID id) {
    barbershopService.deleteBarbershop(id);
    return ResponseEntity.noContent().build();
  }

}
