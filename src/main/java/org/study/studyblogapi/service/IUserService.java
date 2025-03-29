package org.study.studyblogapi.service;

import org.study.studyblogapi.model.dto.ChangePasswordRequest;

import java.security.Principal;

public interface IUserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
