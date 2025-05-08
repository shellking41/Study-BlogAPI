package org.study.studyblogapi.service;

import org.study.studyblogapi.model.dto.response.FollowResponse;
import org.study.studyblogapi.model.dto.response.FollowersResponse;

import java.util.concurrent.CompletableFuture;

public interface IFollowService {
    CompletableFuture<FollowersResponse> GetAllFollowers();
    FollowResponse Follow(Integer userId);
    CompletableFuture<String> testAsync();
    String test();
}
