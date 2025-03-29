package org.study.studyblogapi.mapper;

import org.study.studyblogapi.model.dto.BlogPostRequest;
import org.study.studyblogapi.model.dto.BlogPostResponse;
import org.study.studyblogapi.model.entity.BlogPost;

public class BlogPostMapper {

    public static BlogPost toEntity(BlogPostRequest request) {
        return BlogPost.builder()
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

    }


    public static BlogPostResponse toResponse(BlogPost blogPost) {
        return BlogPostResponse.builder()
                .category(blogPost.getCategory())
                .title(blogPost.getTitle())
                .content(blogPost.getContent())
                .updatedAt(blogPost.getUpdatedAt())
                .createdAt(blogPost.getCreatedAt())
                .createdBy(blogPost.getCreatedBy())
                .build();
    }
}
