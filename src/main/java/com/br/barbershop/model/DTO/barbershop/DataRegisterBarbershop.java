package com.br.barbershop.model.DTO.barbershop;

import com.br.barbershop.model.DTO.address.DataRegisterAddress;

import java.time.LocalDate;

public record DataRegisterBarbershop(
  String ownerName,
  String tradeName,
  String corporateName,
  String email,
  String password,
  String document,
  String phoneNumber,
  LocalDate dateOfBirth,
  DataRegisterAddress barbershopAddress,
  DataRegisterAddress personalAddress
) {
}

/*"ownerName": "Gabriel Fabricio Chagas",
      "tradeName": "Faber Barber",
      "corporateName": "Fabricio Barbearia",
      "email": "hansmaozona@hotmail.com",
      "password": "zEu*ja*WA6",
      "document": "29388417000155",
      "phoneNumber": "21998452291",
      "dateOfBirth": "2002-03-16",
      "barbershopAddress": {
    "addressNumber": "21635380",
        "addressStreet": "Rua Otacílio Pedro Vasco",
        "addressCity": "Rio de Janeiro"
  },
      "personalAddress": {
    "addressNumber": "21635380",
        "addressStreet": "Rua Otacílio Pedro Vasco",
        "addressCity": "Rio de Janeiro"
  }*/
