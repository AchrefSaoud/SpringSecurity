package com.example.demo.Security.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Security.entities.RoleUser;


public interface RoleUserRepo extends JpaRepository<RoleUser,Long >{
    RoleUser findByName(String name);
}
