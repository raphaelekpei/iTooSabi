package com.raphael.itoosabi.data.repository;

import com.raphael.itoosabi.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle(String title);
}
