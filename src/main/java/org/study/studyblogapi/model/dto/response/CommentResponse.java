package org.study.studyblogapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;
    private Integer commenterId;
    private Long postId;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
