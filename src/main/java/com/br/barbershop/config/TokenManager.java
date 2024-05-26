package com.br.barbershop.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenManager {

  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(UUID id, String email, List<String> role) {
    return JWT.create()
      .withClaim("email", email)
      .withClaim("id", id.toString())
      .withClaim("roles", role)
      .withExpiresAt(expirationDate())
      .sign(Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8)));
  }

  public String getEmailFromToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
    DecodedJWT jwt = JWT.require(algorithm)
        .build()
        .verify(token);
    return jwt.getClaim("email").asString();
  }

  public UUID getIdFromToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
    DecodedJWT jwt = JWT.require(algorithm)
        .build()
        .verify(token);
    return UUID.fromString(jwt.getClaim("id").asString());
  }

  public List<String> getRolesFromToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
    DecodedJWT jwt = JWT.require(algorithm)
        .build()
        .verify(token);
    return jwt.getClaim("roles").asList(String.class);
  }

  private Instant expirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

}
