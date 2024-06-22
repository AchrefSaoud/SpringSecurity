package com.example.demo.Security;

import java.util.ArrayList;

import java.util.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Security.entities.AppUser;
import com.example.demo.Security.services.AccountServiceImpl;

@Configuration
public class AppConfig {

	private final AccountServiceImpl accountService;

    public AppConfig(@Lazy AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

 	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser u = accountService.loadByUsername(username);
                if (u == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                u.getRoles().forEach(r -> {
                    authorities.add(new SimpleGrantedAuthority(r.getName()));
                });
                return new User(u.getUsername(), u.getPassword(), authorities);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
