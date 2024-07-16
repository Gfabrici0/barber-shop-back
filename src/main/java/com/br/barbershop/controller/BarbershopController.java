package com.br.barbershop.controller;

import com.br.barbershop.model.DTO.DataFindBarbershop;
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
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.CNPJ;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("barbershop")
@Controller
@Slf4j
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
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP')")
  public ResponseEntity<DataBarbershopService> registerService(@RequestBody @Valid DataRegisterBarbershopService dataRegisterBarbershopService) {
    DataBarbershopService service = barbershopService.registerService(dataRegisterBarbershopService);
    return ResponseEntity.ok().body(service);
  }

  @GetMapping("service/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP')")
  public ResponseEntity<ListBarbershopService> getAllBarbershopServices(@PathVariable UUID id) {
    ListBarbershopService services = barbershopService.getAllBarbershopServices(id);
    return ResponseEntity.ok().body(services);
  }

  @GetMapping("document/{document}")
  public ResponseEntity<DataBarbershopWithoudUser> getBarbershopByDocument(@PathVariable @Valid @CNPJ String document) {
    DataBarbershopWithoudUser barbershop = barbershopService.getBarbershopByDocument(document);
    return ResponseEntity.ok().body(barbershop);
  }

  @PutMapping("service/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP')")
  public ResponseEntity<?> updateBarbershopService(@PathVariable UUID id, @RequestBody DataUpdateBarbershopService dataUpdateBarbershopService) {
    barbershopService.updateBarbershopService(id, dataUpdateBarbershopService);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("service/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP')")
  public ResponseEntity<?> deleteBarbershopServiceById(@PathVariable UUID id) {
    barbershopService.deleteBarbershopServiceById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP', 'USER')")
  public ResponseEntity<Page<DataBarbershopWithoudUser>> getAllBarbershop(@PageableDefault(size = 10, sort = {"document"}) Pageable pageable) {
    Page<DataBarbershopWithoudUser> result = barbershopService.getAllBarbershops(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP', 'USER')")
  public ResponseEntity<DataBarbershop> getBarbershopById(@PathVariable UUID id) {
    DataBarbershop result = barbershopService.getDataBarbershopById(id);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("find/{name}")
  public ResponseEntity<List<DataBarbershop>> findByName(@PathVariable String name) {
    List<DataBarbershop> result = barbershopService.findByName(name);

    return ResponseEntity.ok().body(result);
  }

  @GetMapping("barbers/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP', 'USER')")
  public ResponseEntity<BarbershopWithBarbers> getBarbersFromBarbershop(@PathVariable UUID id) {
    BarbershopWithBarbers barbers = barbershopService.getBarbersFromBarbershop(id);
    return ResponseEntity.ok().body(barbers);
  }

  @GetMapping("pending")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Page<DataBarbershop>> getAllBarbershopsWithPendingStatusById(@PageableDefault(size = 10, sort = {"document"}) Pageable pageable) {
    Page<DataBarbershop> barbershop = barbershopService.getAllBarbershopsWithPendingStatusById(pageable);
    return ResponseEntity.ok().body(barbershop);
  }

  @Transactional
  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'BARBERSHOP')")
  public ResponseEntity<String> updateBarbershop(@PathVariable UUID id,@RequestBody @Valid DataUpdateBarbershop dataUpdateBarbershop) {
    barbershopService.updateBarbershop(id, dataUpdateBarbershop);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> deleteBarbershopById(@PathVariable UUID id) {
    barbershopService.deleteBarbershop(id);
    return ResponseEntity.noContent().build();
  }

}
