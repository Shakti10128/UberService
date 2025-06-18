package org.shakti.uberauthservice.helpers;

import org.shakti.uberauthservice.Models.Driver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthDriverDetails extends Driver implements UserDetails {
    private String username;
    private String password;

    public AuthDriverDetails(Driver driver) {
        this.username = driver.getEmail();
        this.password = driver.getPassword();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
