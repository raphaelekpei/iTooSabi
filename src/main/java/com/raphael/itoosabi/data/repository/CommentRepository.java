package com.raphael.itoosabi.data.repository;

import com.raphael.itoosabi.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findAllByPostId(Long postId);

    Optional<Comment> findByContent(String content);
}
