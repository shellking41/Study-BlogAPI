package org.study.studyblogapi.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.study.studyblogapi.exeption.UserNotFoundException;
import org.study.studyblogapi.model.dto.AuthenticationRequest;
import org.study.studyblogapi.model.dto.AuthenticationResponse;
import org.study.studyblogapi.model.dto.RegisterRequest;
import org.study.studyblogapi.security.config.JwtService;
import org.study.studyblogapi.model.entity.Token;
import org.study.studyblogapi.repository.TokenRepository;
import org.study.studyblogapi.model.enums.TokenType;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.repository.UserRepository;
import org.study.studyblogapi.service.IAuthenticationService;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  private static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
  private static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";

  @Override
  public Map<String,Object> register(RegisterRequest request, HttpServletResponse response) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);

    addTokenCookie(response,ACCESS_TOKEN_COOKIE_NAME,jwtToken,jwtExpiration);
    addTokenCookie(response,REFRESH_TOKEN_COOKIE_NAME,refreshToken,refreshExpiration);

    Map<String,Object> res=new HashMap<>();
    res.put("message","Registration was successful");
 return res;
  }


  @Override
  public Map<String,Object> authenticate(AuthenticationRequest request, HttpServletResponse response) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    addTokenCookie(response,ACCESS_TOKEN_COOKIE_NAME,jwtToken,jwtExpiration);
    addTokenCookie(response,REFRESH_TOKEN_COOKIE_NAME,refreshToken,refreshExpiration);

    Map<String,Object> res=new HashMap<>();
    res.put("message","Login was successful");
    return res;
  }

  @Override
  public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
  ) throws IOException {

      String refreshToken= jwtService.getTokenFromCookies(request, REFRESH_TOKEN_COOKIE_NAME);

      //need exception
      if(refreshToken==null){
          return;
      }

      String userEmail = jwtService.extractUsername(refreshToken);
      if (userEmail != null) {
          var user = this.repository.findByEmail(userEmail)
                  .orElseThrow();
          if (jwtService.isTokenValid(refreshToken, user)) {
              var accessToken = jwtService.generateToken(user);
              revokeAllUserTokens(user);
              saveUserToken(user, accessToken);

              addTokenCookie(response,ACCESS_TOKEN_COOKIE_NAME,accessToken,jwtExpiration);

              var authResponse = AuthenticationResponse.builder()
                      .accessToken(accessToken)
                      .refreshToken(refreshToken)
                      .build();
              new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
          }
      }
  }



    private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }


  private void addTokenCookie(HttpServletResponse response, String name, String token, long expiration) {

    Cookie cookie=new Cookie(name,token);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setSecure(false);
    cookie.setMaxAge((int)expiration/1000);
    response.addCookie(cookie);
  }



}
