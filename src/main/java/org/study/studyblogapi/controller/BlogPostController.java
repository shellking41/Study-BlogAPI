package org.study.studyblogapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.studyblogapi.model.dto.BlogPostRequest;
import org.study.studyblogapi.model.dto.BlogPostResponse;
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
}
