package com.example.full.repository;

import com.example.full.model.Client;
import com.example.full.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("from Region")
    public List<Region> findAllRegiones();
}
