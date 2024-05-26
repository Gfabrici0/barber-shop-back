package com.br.barbershop.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenValidationFilter extends OncePerRequestFilter {

  private final TokenManager tokenManager;

  private final UserService userService;

  public TokenValidationFilter(TokenManager tokenManager, UserService userService) {
    this.tokenManager = tokenManager;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {
    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
      try {
        String token = header.substring(7);
        String email = tokenManager.getEmailFromToken(token);
        UUID id = tokenManager.getIdFromToken(token);
        List<String> roles = tokenManager.getRolesFromToken(token);

        DataUser dataUser = userService.getUserByEmail(email);

        if (validateToken(email, id, roles, dataUser)) {
          if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, getAuthorities(roles));
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
        } else {
          sendUnauthorizedResponse(response, "Invalid token credentials");
          return;
        }
      } catch (TokenExpiredException tokenExpiredException) {
        sendUnauthorizedResponse(response, "Token expired: " + tokenExpiredException.getMessage() + " on " + tokenExpiredException.getExpiredOn());
        return;
      } catch (JWTVerificationException jwtVerificationException) {
        sendUnauthorizedResponse(response, "Invalid token: " + jwtVerificationException.getMessage());
        return;
      }
    }

    chain.doFilter(request, response);
  }

  private boolean validateToken(String email, UUID id, List<String> roles, DataUser dataUser) {
    return email.equals(dataUser.email()) &&
      id.equals(dataUser.id()) &&
      new HashSet<>(roles).equals(dataUser.role().stream().map(userRole -> userRole.role().name()).collect(Collectors.toSet()));
  }

  private List<GrantedAuthority> getAuthorities(List<String> roles) {
    return roles.stream()
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());
  }

  private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write("{\"error\": \"" + message + "\"}");
    response.getWriter().flush();
    response.getWriter().close();
  }

}
