package com.reto.obardales.repository;

import com.reto.obardales.repository.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    public User save(User user);

    public List<User> findAll();

}
