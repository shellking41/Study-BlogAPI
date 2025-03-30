package org.study.studyblogapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;

    @ManyToOne
    @JoinColumn(name="commented_post_id",nullable = false)
    BlogPost blogPost;


    @ManyToOne
    @JoinColumn(name = "commenter_id", nullable = false)
    User commenter;
}
