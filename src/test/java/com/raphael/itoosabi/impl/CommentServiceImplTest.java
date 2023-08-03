package com.raphael.itoosabi.impl;


import com.raphael.itoosabi.data.models.Comment;
import com.raphael.itoosabi.data.models.Post;
import com.raphael.itoosabi.data.repository.CommentRepository;
import com.raphael.itoosabi.data.repository.PostRepository;
import com.raphael.itoosabi.dto.request.CreateCommentRequest;
import com.raphael.itoosabi.dto.request.UpdateCommentRequest;
import com.raphael.itoosabi.dto.response.CreateCommentResponse;
import com.raphael.itoosabi.dto.response.GetCommentResponse;
import com.raphael.itoosabi.dto.response.UpdateCommentResponse;
import com.raphael.itoosabi.exceptions.CommentNotFoundException;
import com.raphael.itoosabi.exceptions.PostNotFoundException;
import com.raphael.itoosabi.service.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ModelMapper modelMapper;

    //  use the @InjectMocks to inject the mocks into the CommentService under test.
    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void commentService_createComment_withValidPostIdAndRequest_shouldCreateCommentAndReturnCreateCommentResponse() {
        // TODO: Given that I have a valid CreateCommentRequest
        CreateCommentRequest createCommentRequest = CreateCommentRequest.builder().content("Believe in yourself").build();

        // TODO: And I create a mock post
        Long postId = 1L;
        Post post = Post.builder().id(postId).title("Test Title").content("Test Content").build();

        // TODO: And I create a mock comment
        Comment comment = Comment.builder().content(createCommentRequest.getContent()).build();

        // TODO: And I initialize the commentList in the post
        post.setCommentList(new ArrayList<>());
        // Note: the commentList in the Post class is null by default.
        // So, We need to initialize an empty list for commentList before performing operations on it.

        // TODO: And I mock the postRepository's findById method to find & return the post
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        when(modelMapper.map(createCommentRequest, Comment.class)).thenReturn(comment);

        // when-thenReturn follows the naming convention of "when X happens, then return Y."
        // TODO: And I mock the commentRepository's save method to save & return the comment
        when(commentRepository.save(comment)).thenReturn(comment);

        // TODO: When I try to create a comment
        CreateCommentResponse response = commentService.createComment(post.getId(), createCommentRequest);

        // TODO: Then I assert that the response contains the correct content
        assertThat(comment).isNotNull();

        // TODO: I assert that the content of the response matches the content of the created comment
        assertThat(response.getContent()).isEqualTo(comment.getContent());

        // TODO: And I assert that the comment is added to the list of comments in the post
        assertThat(post.getCommentList()).contains(comment);

        //  TODO: And I verify that the postRepository's findById method was called
        verify(postRepository, times(1)).findById(postId);

        // TODO: I verify that the commentRepository's save method is called once
        verify(commentRepository, times(1)).save(comment);

        // TODO: I verify that the postRepository's save method is called once'
        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void commentService_createComment_withInvalidPostId_shouldThrowPostNotFoundException() {
        // TODO: Given that I have a createCommentComment Request
        CreateCommentRequest createCommentRequest = CreateCommentRequest.builder().content("Believe in yourself").build();

        // TODO: And I have an invalid postId
        Long postId = 1L;

        // TODO: And I mock the postRepository's findById method to return an empty optional
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When/Then
        // TODO: When I try to create a comment using the invalid postId
        // TODO: Then I assert that a PostNotFoundException is thrown
        assertThatExceptionOfType(PostNotFoundException.class)
                .isThrownBy(() -> commentService.createComment(postId, createCommentRequest));

        // TODO: I verify that the post repository's findById method is called once with the expected post ID
        verify(postRepository, times(1)).findById(postId);

        // TODO: I verify that the comment repository's save method is never called
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    public void commentService_getCommentById_withValidPostIdAndCommentId_shouldReturnCommentResponse() {
        // TODO: Given that I have a valid post and comment
        Long postId = 1L;
        Long commentId = 1L;
        Post post = Post.builder().id(postId).build();
        Comment comment = Comment.builder().id(commentId).content("Believe in yourself").post(post).build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        // TODO: When I try to get a comment
        GetCommentResponse response = commentService.getCommentById(postId, commentId);

        // Then
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        // TODO: I assert that the response contains a content
        assertThat(response).isNotNull();
        // TODO: I assert that the response contains the correct content
        assertThat(response.getContent()).isEqualTo(comment.getContent());
    }

    @Test
    public void commentService_getCommentById_withInValidPostId_shouldThrowPostNotFoundException() {
        // TODO: Given that I have an invalid postId
        Long postId = 1L;

        // TODO: And I have a commentId
        Long commentId = 1L;
        // TODO: And I mock the postRepository's findById method to find the post & return an empty optional
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // TODO:When I try to get the comment
        // TODO: Then I assert that a PostNotFoundException is thrown
        assertThatExceptionOfType(PostNotFoundException.class)
              .isThrownBy(() -> commentService.getCommentById(postId, commentId));

        // TODO: I verify that the postRepository's findById method is called once with the expected post ID'
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    public void commentService_getCommentById_withInValidCommentId_shouldThrowCommentNotFoundException() {
        // TODO: Given that I have a valid postId
        Long postId = 1L;
        Post mockedPost = Post.builder().id(postId).build();

        // TODO: And I have an invalid commentId
        Long commentId = 1L;

        // TODO: And I mock the commentRepository's findById method to return an empty optional
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        // TODO: And I mock the postRepository's findById method to return the mocked post
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockedPost));

        // TODO: When I try to get the comment
        // TODO: Then I assert that a CommentNotFoundException is thrown
        assertThatExceptionOfType(CommentNotFoundException.class)
              .isThrownBy(() -> commentService.getCommentById(postId, commentId));
        // TODO: I verify that the postRepository's findById method is called once with the expected post ID
        verify(postRepository, times(1)).findById(postId);

        // TODO: I verify that the commentRepository's findById method is called once with the expected comment ID
        verify(commentRepository, times(1)).findById(commentId);
    }

    @Test
    public void commentService_getAllCommentsByPostId_withValidPostId_shouldReturnListOfCommentResponses() {
        // Mock post
        // TODO: Given that I have a valid postId
        Long postId = 1L;
        Post post = Post.builder().id(postId).build();
        // Mock comments
        // TODO: And I create a list of mock comments
        Comment comment1 = Comment.builder().id(1L).content("Comment 1").post(post).build();
        Comment comment2 = Comment.builder().id(2L).content("Comment 2").post(post).build();
        List<Comment> comments = Arrays.asList(comment1, comment2);
        post.setCommentList(comments);

        // Mock repository methods
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findAllByPostId(post.getId())).thenReturn(comments);

        // Call the service method
        // TODO: When I try to invoke the comment service to get all comments by the postId
        List<GetCommentResponse> responses = commentService.getCommentsByPostId(postId);

        // Verify the repository method invocations
        // TODO: Then verify that the
        verify(postRepository, times(1)).findById(postId);
        verify(commentRepository, times(1)).findAllByPostId(post.getId());

        // Assert the result
        assertThat(responses).hasSize(2);
        // TODO: And I assert that the response contains a list of comment responses with the correct content
        assertThat(responses.get(0).getContent()).isEqualTo(comment1.getContent());
        assertThat(responses.get(1).getContent()).isEqualTo(comment2.getContent());
    }

    @Test
    public void commentService_getAllCommentsByPostId_withInValidPostId_shouldThrowPostNotFoundException() {
        // TODO: Given that I have an invalid postId
        Long postId = 1L;

        // TODO: And I
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // TODO: When I try to get all comments by the postId
        // TODO: Then I assert that a PostNotFoundException is thrown
        assertThatExceptionOfType(PostNotFoundException.class)
                .isThrownBy(() -> commentService.getCommentsByPostId(postId))
                .withMessage("Post not found");

        // TODO: And I assert that the postRepository's findAll() method was called once
        verify(postRepository, times(1)).findById(postId);
         verify(commentRepository, never()).findAll();
    }

    @Test
    public void commentService_upd___Comment_withValidPostIdAndCommentIdAndRequest_shouldUpdateCommentAndReturnResponse() {
        // TODO: Given that I have a valid updateCommentRequest
        // TODO: And I have a valid postId
        // TODO: And I have a valid commentId
        // TODO: And I mock the post
        // TODO: And I mock the comment
        // TODO: And I mock an updated comment
        // TODO: And I mock the repository methods
        // TODO: When I invoke the service method to update the comment
        // TODO: Then I assert that the response has a comment
        // TODO: And I assert that the comment is updated and the response contains the correct content
        // TODO: And I verify that

        // Mock post
        Long postId = 1L;
        Post post = Post.builder().id(postId).commentList(new ArrayList<>()).build();
        /*
        --> This is same as the 2 lines above, but it is more readable
        Post post = Post.builder().id(postId).build();
        // TODO: And I initialize the commentList in the post
        post.setCommentList(new ArrayList<>());
         */

        // Mock comment
        Long commentId = 1L;
        Comment comment = Comment.builder().id(commentId).post(post).content("Old Comment").build();

        // Mock update request
        UpdateCommentRequest updateRequest = UpdateCommentRequest.builder().content("Updated Comment").build();

        // Mock updated comment
        Comment updatedComment = Comment.builder()
                .id(comment.getId())
                .post(comment.getPost())
                .content(updateRequest.getContent())
                .build();

        // Mock repository method calls
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
//        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment); // anyone works
        when(commentRepository.save(comment)).thenReturn(updatedComment);

        // Call the service method
        UpdateCommentResponse response = commentService.updateComment(postId, commentId, updateRequest);


        // Verify the repository method invocations
        verify(postRepository, times(1)).findById(postId);
        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).save(comment);
        verify(postRepository, times(1)).save(post);

        // Assert the result
        assertThat(response).isNotNull();
        assertThat(response.getContent()).isEqualTo("Updated Comment");
    }

    @Test
    public void commentService_updateComment_withInvalidPostId_shouldThrowPostNotFoundException() {
        // TODO: Given that I have an invalid postId
        Long postId = 1L;
        // TODO: And I have a valid commentId
        Long commentId = 1L;

        // TODO: And I have an updateCommentRequest
        UpdateCommentRequest request = UpdateCommentRequest.builder().content("Updated comment").build();

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When/Then
        // TODO: When I try to update the comment using the invalid postId
        // TODO: Then I assert that a PostNotFoundException is thrown
        assertThatExceptionOfType(PostNotFoundException.class)
                .isThrownBy(() -> commentService.updateComment(postId, commentId, request));
        //
        verify(postRepository).findById(postId);
        verify(commentRepository, never()).findById(commentId);
        verify(commentRepository, never()).save(any(Comment.class));
        verify(postRepository, never()).save(any(Post.class));

    }

    @Test
    public void commentService_updateComment_withInvalidCommentId_shouldThrowCommentNotFoundException() {
        // Mock data
        Long postId = 1L;
        Long commentId = 1L;
        UpdateCommentRequest updateCommentRequest = UpdateCommentRequest.builder().content("Updated Comment").build();

        Post post = Post.builder().id(postId).build();

        // Mock repository calls
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        // Invoke the method and assert the exception
        assertThatExceptionOfType(CommentNotFoundException.class)
                .isThrownBy(() -> commentService.updateComment(postId, commentId, updateCommentRequest))
                .withMessage("Comment with associate post not found");


        // Verify repository calls
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(commentRepository, never()).save(any(Comment.class));
        verify(postRepository, never()).save(any(Post.class));
    }


    @Test
    public void commentService_deleteComment_withValidPostIdAndCommentId_shouldDeleteCommentAndReturnSuccessMessage() {
        // TODO: Given that I have a valid postId and commentId
        Long postId = 1L;
        Long commentId = 1L;
        Post post = Post.builder().id(postId).build();
        Comment comment = Comment.builder().id(commentId).content("Test comment").post(post).build();

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        // TODO: When I try to delete the comment using the postId and commentId
        String result = commentService.deleteComment(postId, commentId);

        // TODO: Then I assert that the comment is deleted and the result message is as expected
        // Then assert that the comment is deleted and a success message is returned
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(commentRepository).delete(comment);

        assertThat(result).isEqualTo("Comment deleted successfully");
    }

    @Test
    public void commentService_deleteComment_withInvalidPostId_shouldThrowPostNotFoundException() {
        // TODO: Given I have an invalid postId and a valid commentId
        Long postId = 1L;
        Long commentId = 1L;

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // TODO: When I try to delete the comment using the invalid postId
        // TODO: Then I assert that a PostNotFoundException is thrown
        assertThatExceptionOfType(PostNotFoundException.class)
                .isThrownBy(() -> commentService.deleteComment(postId, commentId));

        verify(postRepository).findById(postId);
        verify(commentRepository, never()).findById(commentId);
        verify(commentRepository, never()).delete(any(Comment.class));
    }

    @Test
    public void commentService_deleteComment_withInValidCommentId_shouldThrowCommentNotFoundException() {

        // TODO: Given that I have a valid postId
        Long postId = 1L;
        // TODO: And I create a mock post
        Post mockPost = Post.builder().id(postId).build();
        // TODO: And I have an invalid commentId
        Long commentId = 1L;
        // TODO: And I mock the postRepository's findById to find & return the post
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));
        // TODO: When I try to delete the comment
        // TODO: THen I assert that a CommentNotFoundException is thrown
        assertThatExceptionOfType(CommentNotFoundException.class)
              .isThrownBy(() -> commentService.deleteComment(postId, commentId));

        // TODO: Then I verify that the postRepository's findById method was called once
        verify(postRepository, times(1)).findById(postId);
    }
}