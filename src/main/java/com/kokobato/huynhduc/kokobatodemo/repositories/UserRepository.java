package com.kokobato.huynhduc.kokobatodemo.repositories;

import com.kokobato.huynhduc.kokobatodemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
