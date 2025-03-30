package org.study.studyblogapi.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.study.studyblogapi.model.dto.request.AuthenticationRequest;
import org.study.studyblogapi.model.dto.request.RegisterRequest;

import java.io.IOException;
import java.util.Map;

public interface IAuthenticationService {
    Map<String,Object> register (RegisterRequest request, HttpServletResponse response);
    Map<String,Object> authenticate(AuthenticationRequest request,HttpServletResponse response);
    void refreshToken( HttpServletRequest request,HttpServletResponse response)throws IOException;
}
