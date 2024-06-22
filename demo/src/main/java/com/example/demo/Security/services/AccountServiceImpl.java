package com.example.demo.Security.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Security.entities.AppUser;
import com.example.demo.Security.entities.RoleUser;
import com.example.demo.Security.repositries.AppUserRepo;
import com.example.demo.Security.repositries.RoleUserRepo;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepo appUserRepo;
    private RoleUserRepo roleUserRepo;
    private PasswordEncoder passwordEncoder;    

    public AccountServiceImpl(AppUserRepo appUserRepo, RoleUserRepo roleUserRepo,PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.roleUserRepo = roleUserRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return this.appUserRepo.save(appUser);
    }

    @Override
    public RoleUser addNewRole(RoleUser roleUser) {
        return this.roleUserRepo.save(roleUser);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser=this.appUserRepo.findByUsername(username);
        RoleUser roleUser=this.roleUserRepo.findByName(rolename);

        appUser.getRoles().add(roleUser);
    }

    @Override
    public AppUser loadByUsername(String username) {
        return this.appUserRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return this.appUserRepo.findAll();    
    }
    
}
