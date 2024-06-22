package com.example.demo.Security.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Security.RoleUserForm;
import com.example.demo.Security.entities.AppUser;
import com.example.demo.Security.entities.RoleUser;
import com.example.demo.Security.services.AccountService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AccountRestController {
    private AccountService accountService;
    
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return this.accountService.listUsers();
    }
    
    @PostMapping("/users")
    public AppUser AddUser(@RequestBody AppUser user) {
        return this.accountService.addNewUser(user);
    }

    @PostMapping("/roles")
    public RoleUser AddRole(@RequestBody RoleUser role) {
        return this.accountService.addNewRole(role);
    }

    @PostMapping("/addroletouser")
    public void addroletouser(@RequestBody RoleUserForm u) {
        this.accountService.addRoleToUser(u.getUsername(), u.getRolename());
    }   
}