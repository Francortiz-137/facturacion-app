package com.example.full.repository;

import com.example.full.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    /*
    @Query("select p from Productos where p.name like %?1%")
    public List<Product> findByName(String Term);   */

    public List<Product> findByNameContainingIgnoreCase(String Term);

    public List<Product> findByNameStartingWithIgnoreCase(String Term);
}
