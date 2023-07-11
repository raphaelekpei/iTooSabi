package com.raphael.itoosabi.data.repository;

import com.raphael.itoosabi.data.models.Comment;
import com.raphael.itoosabi.data.models.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void commentRepository_SaveComment_ShouldReturnSavedComment(){

        // TODO: given that I have a comment
        // TODO: when I try to save the comment in my database
        // TODO: then I assert that the comment is saved
        // TODO: and I assert that the comment has an Id

        // Given
        Comment comment = Comment.builder().content("Do you love me or not?").build();
        // When
        Comment savedComment = commentRepository.save(comment);
        // Then
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isGreaterThan(0);
    }

    @Test
    public void commentRepository_FindCommentById_ShouldReturnComment(){
        // TODO: given that I have a comment -> in my database
        // TODO: when i try to find & get the comment by Id
        // TODO: then assert that found comment is not null

        // given
        Comment comment = Comment.builder().content("Comment 1").build();
        commentRepository.save(comment);
        // when
        Comment savedComment = commentRepository.findById(comment.getId()).get();
        // then
        assertThat(savedComment).isNotNull();
    }

    @Test
    public void commentRepository_FindCommentByContent_ShouldReturnComment(){
        // TODO: given that I have a comment -> in my database
        // TODO: when i try to find & get the comment by content
        // TODO: then assert that found comment is not null

        // given
        Comment comment = Comment.builder().content("Do you love me or not?").build();
        commentRepository.save(comment);
        // when
        Comment savedComment = commentRepository.findByContent(comment.getContent()).get();
        // assert
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getContent()).isEqualTo("Do you love me or not?");
        assertThat(savedComment.getContent()).startsWith("Do you love");
        assertThat(savedComment.getContent()).endsWith("me or not?");
        assertThat(savedComment.getContent()).contains("Do you");
    }

    @Test
    public void commentRepository_GetAllCommentsByPostId_ShouldReturnMoreThanOneComment(){
        // TODO: Given that I have a post -> in my database
        // TODO: And I have 2 comments associated with the post -> in my database
        // TODO: when I try to get all the comments for the given post Id
        // TODO: then I assert that comments are retrieved correctly

        // Given
        // Create a post in my database
        Post post = Post.builder().id(1L).build();
        postRepository.save(post);

        // Create associated comments in my database
        Comment comment1 = Comment.builder().post(post).content("Comment 1").build();
        Comment comment2 = Comment.builder().post(post).content("Comment 2").build();

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<Comment> comments = commentRepository.findAllByPostId(post.getId());

        // then
        assertThat(comments).isNotNull();
        assertThat(comments).hasSize(2);
        //        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments.get(0).getContent()).isEqualTo("Comment 1");
        assertThat(comments.get(1).getContent()).isEqualTo("Comment 2");
    }

    @Test
    public void commentRepository_GetAllComments_ShouldReturnAllComments(){
        // TODO: given that I have 2 comments in my database
        // TODO: when I try to find & get all the comments
        // TODO: then assert that

        Comment comment1 = Comment.builder().content("Comment 1").build();
        Comment comment2 = Comment.builder().content("Comment 2").build();
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<Comment> comments = commentRepository.findAll();

        // then
        assertThat(comments).isNotNull();
        assertThat(comments).hasSize(2);
        //        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments.get(0).getContent()).isEqualTo("Comment 1");
        assertThat(comments.get(1).getContent()).isEqualTo("Comment 2");
    }

}