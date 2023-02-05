package com.example.full.service;

import com.example.full.model.Client;
import com.example.full.model.Invoice;
import com.example.full.model.Product;
import com.example.full.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Page<Client> findAll(Pageable pageable);

    Client findById(Long id);

    Client save(Client client);

    void delete(Long id);
    public List<Region> findAllRegiones();

    public Invoice findInvoiceById(Long id);

    public Invoice saveInvoice(Invoice invoice);

    public void deleteInvoiceById(Long id);

    public List<Product> findProductByName(String term);
}
