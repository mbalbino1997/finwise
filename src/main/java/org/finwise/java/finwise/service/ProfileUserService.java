package org.finwise.java.finwise.service;

import org.finwise.java.finwise.model.ProfileUser;
import org.finwise.java.finwise.repository.ProfileUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileUserService {

    private final ProfileUserRepository repository;

    public ProfileUserService(ProfileUserRepository repository) {
        this.repository = repository;
    }

    public List<ProfileUser> findAll() {
        return repository.findAll();
    }

    public ProfileUser findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
