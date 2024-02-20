package com.br.barbershop.helpers;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TestContext {

  private UUID userUUID;

  public UUID getUserUUID() {
    return userUUID;
  }

  public void setUserUUID(UUID userUUID) {
    this.userUUID = userUUID;
  }

}
