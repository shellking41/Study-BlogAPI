package org.study.studyblogapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.study.studyblogapi.exception.UserNotFoundException;
import org.study.studyblogapi.mapper.UserMapper;
import org.study.studyblogapi.model.dto.response.UserResponse;
import org.study.studyblogapi.repository.UserRepository;
import org.study.studyblogapi.model.dto.request.ChangePasswordRequest;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.service.IUserService;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    public UserResponse getAuthenticatedUserResponse() {
        User user = getAuthenticatedUser();
       return UserMapper.toResponse(user);
    }

    
    public User getAuthenticatedUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication==null || authentication instanceof AnonymousAuthenticationToken || !authentication.isAuthenticated()){
            throw new UserNotFoundException("User not found");
        }

        Integer userid=((User)authentication.getPrincipal()).getId();
        return userRepository.findById(userid)
                .orElseThrow(()->new UserNotFoundException("User not found"));


    }


}
