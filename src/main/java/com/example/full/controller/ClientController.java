package com.example.full.controller;

import com.example.full.model.Client;
import com.example.full.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private static ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<Client> listClients(){
        return  clientService.findAll();
    }

}
