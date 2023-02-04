package com.example.full.repository;

import com.example.full.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice,Long> {
}
