package org.study.studyblogapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.studyblogapi.model.dto.ChangePasswordRequest;
import org.study.studyblogapi.model.dto.UserResponse;
import org.study.studyblogapi.service.IUserService;
import org.study.studyblogapi.service.impl.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public UserResponse getAuthenticatedUserResponse() {
        return userService.getAuthenticatedUserResponse();
    }
}
