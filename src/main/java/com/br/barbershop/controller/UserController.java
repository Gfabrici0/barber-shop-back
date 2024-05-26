package com.br.barbershop.controller;

import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.DTO.user.DataRegisterUser;
import com.br.barbershop.model.DTO.user.DataUpdateUser;
import com.br.barbershop.model.entity.User;
import com.br.barbershop.service.UserService;
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
@RequestMapping("user")
@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Transactional
  @PostMapping
  public ResponseEntity<DataUser> registerUser(@RequestBody @Valid DataRegisterUser userRegisterDto) {
    User createdUser = userService.registerUser(userRegisterDto);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdUser.getId())
        .toUri();

    return ResponseEntity.created(location).body(new DataUser(createdUser));
  }

  @GetMapping
  public ResponseEntity<Page<DataUser>> getAllUser(@PageableDefault(size = 10, sort = {"document"}) Pageable pageable) {
    Page<DataUser> result = userService.getAllUsers(pageable);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataUser> getUserById(@PathVariable UUID id) {
    DataUser result = userService.getUserById(id);
    return ResponseEntity.ok().body(result);
  }

  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<String> updateUser(@PathVariable UUID id,@RequestBody @Valid DataUpdateUser dataUpdateUser) {
    userService.updateUser(id, dataUpdateUser);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

}
