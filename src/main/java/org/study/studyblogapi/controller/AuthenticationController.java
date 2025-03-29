package org.study.studyblogapi.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.study.studyblogapi.model.dto.AuthenticationRequest;
import org.study.studyblogapi.service.IAuthenticationService;
import org.study.studyblogapi.model.dto.RegisterRequest;
import org.study.studyblogapi.service.impl.LogoutService;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final IAuthenticationService authenticationService;
  private final LogoutService logoutService;


  @PostMapping("/register")
  public ResponseEntity<Map<String,Object>> register(
      @Valid @RequestBody RegisterRequest request,
      HttpServletResponse response
  ) {
    return ResponseEntity.ok(authenticationService.register(request,response));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<Map<String,Object>> authenticate(
     @Valid @RequestBody AuthenticationRequest request,
       HttpServletResponse response
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request,response));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    authenticationService.refreshToken(request, response);
  }
 @PutMapping("/logout")
  public void logout(
         HttpServletRequest request,
         HttpServletResponse response,
         Authentication authentication
 ){
   logoutService.logout(request,response,authentication);
 }

}
