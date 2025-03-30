package org.study.studyblogapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="blog_post")
public class BlogPost  extends  BaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String category;

    @OneToMany(mappedBy = "blogPost",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "blog_posts_tags",
            joinColumns = @JoinColumn(name="blog_post_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="tag_id",nullable = false)
    )
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User author;

    @ManyToMany
    @JoinTable(
            name="likes",
            joinColumns = @JoinColumn(name = "likedByUser_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="likedPost_id",nullable = false)
    )
    private List<User> likedByUsers;
}
