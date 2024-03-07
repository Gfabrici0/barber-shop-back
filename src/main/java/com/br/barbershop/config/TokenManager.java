package com.br.barbershop.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenManager {

  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(String email) {
    return JWT.create()
      .withSubject(email)
      .withExpiresAt(expirationDate())
      .sign(Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8)));
  }

  public String validateTokenAndGetEmail(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
    DecodedJWT jwt = JWT.require(algorithm)
        .build()
        .verify(token);
    return jwt.getSubject();
  }

  private Instant expirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

}
