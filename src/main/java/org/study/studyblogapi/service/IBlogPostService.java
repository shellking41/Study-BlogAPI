package org.study.studyblogapi.service;

import org.study.studyblogapi.model.dto.request.BlogPostRequest;
import org.study.studyblogapi.model.dto.response.BlogPostResponse;

public interface IBlogPostService {

 BlogPostResponse createBlogPost (BlogPostRequest request);
 boolean toggleLike(Long postId);
}
