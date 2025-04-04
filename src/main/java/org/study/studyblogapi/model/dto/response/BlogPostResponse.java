package org.study.studyblogapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostResponse {

    private Long id;
    private String title;
    private String content;
    private String category;
    private int likeCount;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
