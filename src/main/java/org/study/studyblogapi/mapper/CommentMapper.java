package org.study.studyblogapi.mapper;

import org.study.studyblogapi.model.dto.request.CommentRequest;
import org.study.studyblogapi.model.dto.response.CommentResponse;
import org.study.studyblogapi.model.entity.BlogPost;
import org.study.studyblogapi.model.entity.Comment;
import org.study.studyblogapi.model.entity.User;

public class CommentMapper {

    public static Comment toEntity(CommentRequest request, User commenter, BlogPost blogPost){
        return Comment.builder()
                .content(request.getContent())
                .commenter(commenter)
                .blogPost(blogPost)
                .build();
    }

    public static CommentResponse toResponse(Comment comment){
        return CommentResponse.builder()
                .Id(comment.getId())
                .commenterId(comment.getCommenter().getId())
                .postId(comment.getBlogPost().getId())
                .content(comment.getContent())
                .createdBy(comment.getCreatedBy())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
