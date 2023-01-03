package com.example.full.controller;

import com.example.full.model.Client;
import com.example.full.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    private static ClientService clientService;

    @GetMapping("/")
    public List<Client> listClients(){
        return  clientService.findAll();
    }

}
