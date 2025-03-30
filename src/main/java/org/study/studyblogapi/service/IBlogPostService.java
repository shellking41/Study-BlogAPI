package org.study.studyblogapi.service;

import org.study.studyblogapi.model.dto.BlogPostRequest;
import org.study.studyblogapi.model.dto.BlogPostResponse;

public interface IBlogPostService {

 BlogPostResponse createBlogPost (BlogPostRequest request);
 boolean toggleLike(Long postId);
}
