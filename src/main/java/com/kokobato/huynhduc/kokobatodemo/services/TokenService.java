package com.kokobato.huynhduc.kokobatodemo.services;

import com.kokobato.huynhduc.kokobatodemo.component.JwtTokenUtil;
import com.kokobato.huynhduc.kokobatodemo.models.Token;
import com.kokobato.huynhduc.kokobatodemo.models.User;
import com.kokobato.huynhduc.kokobatodemo.repositories.TokenRepository;
import com.kokobato.huynhduc.kokobatodemo.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final TokenRepository tokenRepository;

    @Value("${jwt.secretKey}")
    private String secretKey;

    public Token addToken(User user, String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        Token jwtToken = Token.builder()
                .token(token)
                .token_type("Access Token")
                .user(user)
                .refresh_token(UUID.randomUUID().toString())
                .refresh_expiration_date(LocalDateTime.now().plusDays(30))
                .expiration_date(claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .build();

        tokenRepository.save(jwtToken);
        return jwtToken;
    }
}
