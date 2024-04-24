package com.reto.obardales.repository.jpa;

import com.reto.obardales.repository.UserRepository;
import com.reto.obardales.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String>, UserRepository {


}
