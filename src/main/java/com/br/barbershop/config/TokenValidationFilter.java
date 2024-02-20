package com.br.barbershop.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class TokenValidationFilter extends OncePerRequestFilter {

  private final TokenManager tokenManager;

  public TokenValidationFilter(TokenManager tokenManager) {
    this.tokenManager = tokenManager;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
      try {
        String token = header.substring(7);
        String username = tokenManager.validateTokenAndGetUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, null);
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      } catch (TokenExpiredException tokenExpiredException) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\": \"Token expired: " + tokenExpiredException.getMessage() + " on " + tokenExpiredException.getExpiredOn() + "\"}");
        return;
      } catch (JWTVerificationException jwtVerificationException) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\": \"Invalid token: " + jwtVerificationException.getMessage() + "\"}");
        return;
      }
    }

    chain.doFilter(request, response);
  }

}
