package com.raphael.itoosabi.data.repository;

import com.raphael.itoosabi.data.models.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void postRepository_Save_ShouldSavePostAndReturnSavedPost(){
        // TODO: Given that I have a post
        // TODO: when I try to save the post in my database
        // TODO: Then I assert that the post is saved
        // TODO: And I assert that the post has an id

        // Given
        Post post = Post.builder().title("Test Title").content("Test Content").build();

        // When
        Post savedPost = postRepository.save(post);

        // Assert
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getId()).isGreaterThan(0);
    }

    @Test
    public void postRepository_FindById_ShouldReturnPost(){
        // TODO: Given that I have a post -> already saved in my database
        // TODO: when I try to find & get the post by Id
        // TODO: then assert that the post exists

        // Given
        Post post = Post.builder().title("Test Title").content("Test Content").build();
        postRepository.save(post);
        // When
        postRepository.findById(post.getId());
       // Assert
        assertThat(post).isNotNull();
    }

    @Test
    public void postRepository_FindByTitle_ShouldReturnPost(){
        // TODO: given that I have a post -> in my database
        // TODO: when I try to get the post by title
        // TODO: Then I assert that the post has been saved

        // Given
        Post post = Post.builder().title("Test Title").content("Test Content").build();
        postRepository.save(post);

        // When
        Post savedPost = postRepository.findByTitle(post.getTitle()).get();

        // Assert
        assertThat(savedPost).isNotNull();

        assertThat(savedPost.getTitle()).isEqualTo("Test Title");
        assertThat(savedPost.getContent()).isEqualTo("Test Content");
    }

    @Test
    public void postRepository_GetAll_ShouldReturnMoreThanOnePost(){
        // TODO: given that I have 2 posts -> in my database
        // TODO: when I try to get all the posts
        // TODO: Then I assert that the post list is not null
        // TODO: And I assert that the post list size is equal to 2

        // Given
        Post post1 = Post.builder().title("Test Title 1").content("Test Content 1").build();
        postRepository.save(post1);

        Post post2 = Post.builder().title("Test Title 2").content("Test Content 2").build();
        postRepository.save(post2);

        // when
        List<Post> postList = postRepository.findAll();

        // Assert
        assertThat(postList).isNotNull();
        assertThat(postList).hasSize(2);
    }

    @Test
    public void postRepository_UpdatePost_ShouldReturnPostNotNull(){
        // TODO: given that I have a post -> already saved in my database
        // TODO: when I try to get the post & change its title and content
        // TODO: then assert that the post saved is different from the updated post

        // Given
        Post post = Post.builder().title("Old Title").content("Old Content").build();
        postRepository.save(post);

        // When
        Post savedPost = postRepository.findById(post.getId()).get();
        savedPost.setTitle("Updated Title");
        savedPost.setContent("Updated Content");
        Post updatedPost = postRepository.save(savedPost);

        // Assert
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedPost.getContent()).isEqualTo("Updated Content");
    }

    @Test
    public void postRepository_DeletePost_ShouldReturnPostIsNull() {
        // TODO: given that I have a post -> in my database
        // TODO: when I delete the post & try to get it again
        // TODO: then I assert that the post is no longer in the database

        // Given
        Post post = Post.builder().id(1L).title("Test Title").content("Test Content").build();
        postRepository.save(post);

        // When
        postRepository.deleteById(post.getId());
        Optional<Post> deletedPost = postRepository.findById(post.getId());

        // Assert
        assertThat(deletedPost).isEmpty();
    }
}