package org.study.studyblogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.studyblogapi.model.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
}
