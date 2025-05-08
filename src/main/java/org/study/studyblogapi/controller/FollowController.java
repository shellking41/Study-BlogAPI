package org.study.studyblogapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.studyblogapi.model.dto.response.FollowResponse;
import org.study.studyblogapi.model.dto.response.FollowersResponse;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.service.IFollowService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FollowController {

    private final IFollowService followService;

    @GetMapping("/follower/all")
    public CompletableFuture<ResponseEntity<FollowersResponse>> getAllFollowers() {
        return followService.GetAllFollowers()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/follower/testasync")
    public CompletableFuture<String> testAsync(){

        return followService.testAsync();
    }

    @GetMapping("/follower/test")
    public String test(){

        return followService.test();
    }
    @PostMapping("/follow/{userId}")
    public FollowResponse Follow(@PathVariable Integer userId) {
        return followService.Follow(userId);
    }
}
