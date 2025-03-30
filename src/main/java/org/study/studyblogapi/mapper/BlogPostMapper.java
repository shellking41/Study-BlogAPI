package org.study.studyblogapi.mapper;

import org.study.studyblogapi.model.dto.request.BlogPostRequest;
import org.study.studyblogapi.model.dto.response.BlogPostResponse;
import org.study.studyblogapi.model.entity.BlogPost;
import org.study.studyblogapi.model.entity.User;

public class BlogPostMapper {

    public static BlogPost toEntity(BlogPostRequest request, User user) {
        return BlogPost.builder()
                .category(request.getCategory())
                .title(request.getTitle())
                .author(user)
                .content(request.getContent())
                .build();

    }


    public static BlogPostResponse toResponse(BlogPost blogPost) {
        return BlogPostResponse.builder()
                .id(blogPost.getId())
                .category(blogPost.getCategory())
                .title(blogPost.getTitle())
                .content(blogPost.getContent())
                .likeCount(blogPost.getLikeCount())
                .updatedAt(blogPost.getUpdatedAt())
                .createdAt(blogPost.getCreatedAt())
                .createdBy(blogPost.getCreatedBy())
                .build();
    }
}
