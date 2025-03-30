package org.study.studyblogapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.study.studyblogapi.model.dto.request.BlogPostRequest;
import org.study.studyblogapi.model.dto.response.BlogPostResponse;
import org.study.studyblogapi.service.IBlogPostService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/BlogPost")
public class BlogPostController {

    final IBlogPostService blogPostService;

    @PostMapping("/Create")
    public BlogPostResponse createBlogPost(@RequestBody BlogPostRequest request) {

        return blogPostService.createBlogPost(request);

    }

    @PutMapping("/toggleLike/{postId}")
    public boolean toggleLike(@PathVariable Long postId){
        return blogPostService.toggleLike(postId);
    }
}
