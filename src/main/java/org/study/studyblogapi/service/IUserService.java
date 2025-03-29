package org.study.studyblogapi.service;

import org.study.studyblogapi.model.dto.ChangePasswordRequest;
import org.study.studyblogapi.model.dto.UserResponse;
import org.study.studyblogapi.model.entity.User;

import java.security.Principal;

public interface IUserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    UserResponse getAuthenticatedUserResponse();
    User getAuthenticatedUser();
}
