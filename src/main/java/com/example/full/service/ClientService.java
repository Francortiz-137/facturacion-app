package com.example.full.service;

import com.example.full.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    void delete(Long id);
}
