package org.study.studyblogapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.studyblogapi.exception.BlogPostNotFoundException;
import org.study.studyblogapi.mapper.CommentMapper;
import org.study.studyblogapi.model.dto.request.CommentRequest;
import org.study.studyblogapi.model.dto.response.CommentResponse;
import org.study.studyblogapi.model.entity.BlogPost;
import org.study.studyblogapi.model.entity.Comment;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.repository.BlogPostRepository;
import org.study.studyblogapi.repository.CommentRepository;
import org.study.studyblogapi.service.ICommentService;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final BlogPostRepository blogPostRepository;


    @Override
    @Transactional
    public CommentResponse addComment(Long postId, CommentRequest request) {
        User commenter=userService.getAuthenticatedUser();
        BlogPost blogPost=blogPostRepository.findById(postId)
                .orElseThrow(()->new BlogPostNotFoundException("Post not found"));

        Comment comment= CommentMapper.toEntity(request,commenter,blogPost);

        commentRepository.save(comment);

        return CommentMapper.toResponse(comment);
    }
}
