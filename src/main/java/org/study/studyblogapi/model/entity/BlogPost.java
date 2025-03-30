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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String category;
    private int likeCount;

    @OneToMany(mappedBy = "blogPost",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "blog_posts_tags",
            joinColumns = @JoinColumn(name= "blog_post_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name= "tag_id",nullable = false)
    )
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User author;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name= "likes",
            joinColumns = @JoinColumn(name = "likedPost_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name= "likedByUser_id",nullable = false)
    )
    private List<User> likedByUsers;


    //megcsinalja azokat  a dolgokat amiket a adatbazis feltotelse elott kellene csinalni
    @PrePersist
    protected void onCreate(){
        likeCount=0;
    }
}
