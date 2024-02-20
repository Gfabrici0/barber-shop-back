package com.br.barbershop.controller;

import com.br.barbershop.model.DTO.DataClients;
import com.br.barbershop.model.DTO.DataRegisterClient;
import com.br.barbershop.model.entity.User;
import com.br.barbershop.service.ClientAuthService;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/client")
@Controller
public class ClientAuthController {

  @Autowired
  private ClientAuthService clientAuthService;

  @Transactional
  @PostMapping
  public ResponseEntity<DataClients> registerClient(@RequestBody @Valid DataRegisterClient clientRegistreDto) {
    User createdClient = clientAuthService.registerClient(clientRegistreDto);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdClient.getId())
        .toUri();

    return ResponseEntity.created(location).body(new DataClients(createdClient));
  }

  @GetMapping
  public ResponseEntity<Page<DataClients>> getAllClients(@PageableDefault(size = 10, sort = {"document"}) Pageable pageable) {
    Page<DataClients> result = clientAuthService.getAllClients(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<DataClients>> getClientById(@PathVariable UUID id) {
    Optional<DataClients> result = clientAuthService.getClientById(id);
    return ResponseEntity.ok().body(result);
  }

  @Transactional
  @PutMapping
  public ResponseEntity<String> updateClient() {
    return null;
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteClient(@PathVariable UUID id) {
    clientAuthService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }

}
