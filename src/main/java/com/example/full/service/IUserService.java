package com.example.full.service;

import com.example.full.model.AppUser;

public interface IUserService {
    public AppUser findByUsername(String username);
}
