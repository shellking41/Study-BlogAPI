package org.study.studyblogapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.studyblogapi.model.dto.BlogPostRequest;
import org.study.studyblogapi.model.dto.BlogPostResponse;
import org.study.studyblogapi.model.entity.BlogPost;
import org.study.studyblogapi.repository.BlogPostRepository;
import org.study.studyblogapi.service.IBlogPostService;

@Service
@RequiredArgsConstructor
public class BlogPostService implements IBlogPostService {

   final BlogPostRepository blogPostRepository;

    @Override
    public BlogPostResponse createBlogPost(BlogPostRequest request) {
       BlogPost blogPost=blogPostRepository.save(BlogPost.builder()
                       .category(request.getCategory())
                       .title(request.getTitle())
                       .content(request.getContent())
                       .build()
                       );
       return BlogPostResponse.builder()
               .category(blogPost.getCategory())
               .title(blogPost.getTitle())
               .content(blogPost.getContent())
               .createdAt(blogPost.getCreatedAt())
               .createdBy(blogPost.getCreatedBy())
               .updatedAt(blogPost.getUpdatedAt())
               .build();
        }
}