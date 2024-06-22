package com.example.demo.Security.services;

import java.util.List;

import com.example.demo.Security.entities.AppUser;
import com.example.demo.Security.entities.RoleUser;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    RoleUser addNewRole(RoleUser roleUser);
    void addRoleToUser(String username,String rolename);
    AppUser loadByUsername(String username);
    List<AppUser> listUsers();
}
