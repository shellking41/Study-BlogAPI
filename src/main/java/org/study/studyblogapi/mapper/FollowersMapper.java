package org.study.studyblogapi.mapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.study.studyblogapi.model.dto.response.FollowResponse;
import org.study.studyblogapi.model.dto.response.FollowersResponse;
import org.study.studyblogapi.model.dto.response.UserIconResponse;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.service.impl.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FollowersMapper {
    private final TransactionTemplate transactionTemplate;
    private final UserService userService;

    @Transactional
    public FollowersResponse GetAllFollowersTransactional(){

        User user = userService.getAuthenticatedUser();
        return toResponse(user);
    }

    public FollowersResponse toResponse(User user) {

            // All operations with lazy-loaded collections must be inside the transaction
            List<FollowResponse> followResponses = user.getFollowers().stream()
                    .map(follower -> {
                        // Handle potential null UserIcon
                        UserIconResponse iconResponse = null;
                        if (follower.getUserIcon() != null) {
                            iconResponse = UserIconResponse.builder()
                                    .id(follower.getUserIcon().getId())
                                    .path(follower.getUserIcon().getPath())
                                    .usageType(follower.getUserIcon().getUsageType())
                                    .userId(follower.getId())
                                    .build();
                        }

                        return FollowResponse.builder()
                                .followerId(follower.getId())
                                .firstname(follower.getFirstname())
                                .lastname(follower.getLastname())
                                .email(follower.getEmail())
                                .userIcon(iconResponse)
                                .build();
                    })
                    .toList();

            return FollowersResponse.builder()
                    .followers(followResponses)
                    .build();

    }
}