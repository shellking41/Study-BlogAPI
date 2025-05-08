package org.study.studyblogapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionTemplate;
import org.study.studyblogapi.exception.UserNotFoundException;
import org.study.studyblogapi.mapper.FollowersMapper;
import org.study.studyblogapi.model.dto.response.FollowResponse;
import org.study.studyblogapi.model.dto.response.FollowersResponse;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.repository.UserRepository;
import org.study.studyblogapi.service.IFollowService;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor

//mapper hiányzik!!!
public class FollowService implements IFollowService {

    private final FollowersMapper followersMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TransactionTemplate transactionTemplate;

    @Override
    @Async("securedTaskExecutor") // Specify the executor bean name

    public CompletableFuture<FollowersResponse> GetAllFollowers() {
        User user = userService.getAuthenticatedUser();
        return CompletableFuture.completedFuture(followersMapper.GetAllFollowersTransactional());
    }


    @Override
    @Async("securedTaskExecutor")

    // Specify the executor bean name
    public CompletableFuture<String> testAsync(){
        User user = userService.getAuthenticatedUser();
        return CompletableFuture.completedFuture("hi");
    }



    public String test(){
        User user = userService.getAuthenticatedUser();
        return "hi";
    }
    @Override
    public FollowResponse Follow(Integer userId) {
        User user=userService.getAuthenticatedUser();
        User followedUser=userRepository.findById(userId)
                        .orElseThrow(()->new UserNotFoundException("Can't find the followed user"));
        followedUser.getFollowers().add(user);
        user.getFollowing().add(followedUser);

        userRepository.save(user);
        return FollowResponse.builder()


                .build();
    }
}
