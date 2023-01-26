package com.example.full.repository;

import com.example.full.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByUsername(String username);
}
