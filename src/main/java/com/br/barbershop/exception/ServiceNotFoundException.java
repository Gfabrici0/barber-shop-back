package com.br.barbershop.exception;

public class ServiceNotFoundException extends RuntimeException {

  public ServiceNotFoundException(String message) {
    super(message);
  }

}
