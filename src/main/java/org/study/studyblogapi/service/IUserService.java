package org.study.studyblogapi.service;

import org.study.studyblogapi.model.dto.request.ChangePasswordRequest;
import org.study.studyblogapi.model.dto.response.UserResponse;
import org.study.studyblogapi.model.entity.User;

import java.security.Principal;

public interface IUserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    UserResponse getAuthenticatedUserResponse();
    User getAuthenticatedUser();
}
