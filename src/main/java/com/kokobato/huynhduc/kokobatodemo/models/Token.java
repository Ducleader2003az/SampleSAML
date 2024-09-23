package com.kokobato.huynhduc.kokobatodemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token", nullable = false, length = 255)
    private String token;

    @Column(name = "token_type", nullable = true, length = 50)
    private String token_type;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expiration_date;

    @Column(name = "revoked", nullable = true)
    private int revoked;

    @Column(name = "expired", nullable = true)
    private int expired;

    @Column(name = "refresh_token", nullable = false, length = 255)
    private String refresh_token;

    @Column(name = "resfresh_expiration_date", nullable = false)
    private LocalDateTime refresh_expiration_date;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
