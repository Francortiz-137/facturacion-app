package com.example.full.service.impl;

import com.example.full.model.Client;
import com.example.full.model.Invoice;
import com.example.full.model.Product;
import com.example.full.model.Region;
import com.example.full.repository.ClientRepository;
import com.example.full.repository.IInvoiceRepository;
import com.example.full.repository.IProductRepository;
import com.example.full.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private static ClientRepository clientRepository;
    private static IInvoiceRepository invoiceRepository;
    private static IProductRepository productRepository;

    public ClientServiceImpl(ClientRepository clientRepository, IInvoiceRepository invoiceRepository,
                             IProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones() {
        return clientRepository.findAllRegiones();
    }

    @Override
    @Transactional
    public Invoice findInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    @Transactional
    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByName(String term) {
        return productRepository.findByNameContainingIgnoreCase(term);
    }
}
