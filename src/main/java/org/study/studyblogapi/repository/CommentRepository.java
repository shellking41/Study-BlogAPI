package org.study.studyblogapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.study.studyblogapi.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
