package org.study.studyblogapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.study.studyblogapi.model.dto.request.CommentRequest;
import org.study.studyblogapi.model.dto.response.CommentResponse;
import org.study.studyblogapi.service.ICommentService;

@RestController
@RequiredArgsConstructor

@RequestMapping("api/v1/comment")
public class CommentController {
    private final ICommentService commentService;

    @PostMapping("/{postId}")
    public CommentResponse addComment(@PathVariable Long postId, CommentRequest request){
        return commentService.addComment(postId,request);
    }
}
