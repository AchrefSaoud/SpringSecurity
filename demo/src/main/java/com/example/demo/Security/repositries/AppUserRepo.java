package com.example.demo.Security.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Security.entities.AppUser;


public interface AppUserRepo  extends JpaRepository<AppUser,Long>{
    AppUser findByUsername(String username);
}
