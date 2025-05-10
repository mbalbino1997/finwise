package org.finwise.java.finwise.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.finwise.java.finwise.model.Role;
import org.finwise.java.finwise.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails{
    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<GrantedAuthority>();
        for (Role userRole : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.getName()));
        }
        
    }
    
    

    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }



    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }



    public Integer getId() {
        return id;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    
}