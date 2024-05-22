package com.br.barbershop.config;

import com.br.barbershop.model.DTO.token.TokenResponse;
import com.br.barbershop.model.DTO.user.DataUser;
import com.br.barbershop.model.DTO.user.UserCredentials;
import com.br.barbershop.service.UserAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticatorConfig extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  private final TokenManager tokenManager;

  private final UserAuthService userAuthService;

  public AuthenticatorConfig(AuthenticationManager authenticationManager, TokenManager tokenManager, UserAuthService userAuthService) {
    this.authenticationManager = authenticationManager;
    this.tokenManager = tokenManager;
    this.userAuthService = userAuthService;
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {
    try {
      UserCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          credentials.email(), credentials.password());

      return authenticationManager.authenticate(authenticationToken);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException {
    UserDetails userDetails = (UserDetails) authResult.getPrincipal();
    String email = userDetails.getUsername();

    DataUser user = userAuthService.getUserByEmail(email);

    String token = tokenManager.generateToken(
      user.id(),
      user.email(),
      user.role().stream().map(
        userRole -> userRole.role().getRole()
      ).toList()
    );

    response.addHeader("Authorization", "Bearer " + token);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    TokenResponse tokenResponse = new TokenResponse(token);

    response.getWriter().write(new ObjectMapper().writeValueAsString(tokenResponse));
    response.getWriter().flush();
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException failed) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write("Authentication Failed: " + failed.getMessage());
  }

}
