package com.br.barbershop.service;

import com.br.barbershop.model.DTO.DataClients;
import com.br.barbershop.model.DTO.DataRegisterClient;
import com.br.barbershop.model.entity.User;
import com.br.barbershop.repository.ClientAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientAuthService {

  @Autowired
  private ClientAuthRepository clientAuthRepository;

  public User registerClient(DataRegisterClient dataRegisterClient) {
    return clientAuthRepository.save(new User(dataRegisterClient));
  }

  public Page<DataClients> getAllClients(Pageable pageable) {
    return clientAuthRepository.findAll(pageable).map(DataClients::new);
  }

  public Optional<DataClients> getClientById(UUID id) {
    return clientAuthRepository.findById(id).map(DataClients::new);
  }

  public void deleteClient(UUID clientId) {
    clientAuthRepository.deleteById(clientId);
  }
}
