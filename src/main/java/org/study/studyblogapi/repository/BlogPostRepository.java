package org.study.studyblogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.study.studyblogapi.model.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END " +
            "FROM BlogPost p JOIN p.likedByUsers l " +
            "WHERE p.id = :postId AND l.id = :userId")
    boolean hasUserLikedPost(@Param("postId") Long postId, @Param("userId") Integer userId);
}
