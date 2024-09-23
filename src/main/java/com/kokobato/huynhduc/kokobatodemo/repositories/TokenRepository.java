package com.kokobato.huynhduc.kokobatodemo.repositories;

import com.kokobato.huynhduc.kokobatodemo.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
}
