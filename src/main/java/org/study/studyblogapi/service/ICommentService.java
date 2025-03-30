package org.study.studyblogapi.service;

import org.study.studyblogapi.model.dto.request.CommentRequest;
import org.study.studyblogapi.model.dto.response.CommentResponse;

public interface ICommentService {

     CommentResponse addComment(Long postId, CommentRequest request);


}
