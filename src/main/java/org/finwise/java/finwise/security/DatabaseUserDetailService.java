package org.finwise.java.finwise.security;

import java.util.Optional;

import org.finwise.java.finwise.model.User;
import org.finwise.java.finwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class DatabaseUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userAttempt  = userRepository.findByUsername(username);

        if(userAttempt.isEmpty()) {

            throw new UsernameNotFoundException("There are no users available with username"+ username);
        }
        return new DatabaseUserDetails(userAttempt.get());
    }

    
}