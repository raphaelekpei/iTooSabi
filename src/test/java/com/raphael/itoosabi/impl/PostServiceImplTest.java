package com.raphael.itoosabi.impl;


import com.raphael.itoosabi.data.models.Post;
import com.raphael.itoosabi.data.repository.PostRepository;
import com.raphael.itoosabi.dto.request.CreatePostRequest;
import com.raphael.itoosabi.dto.request.UpdatePostRequest;
import com.raphael.itoosabi.dto.response.CreatePostResponse;
import com.raphael.itoosabi.dto.response.GetPostResponse;
import com.raphael.itoosabi.dto.response.GetPostsResponse;
import com.raphael.itoosabi.dto.response.UpdatePostResponse;
import com.raphael.itoosabi.exceptions.PostNotFoundException;
import com.raphael.itoosabi.service.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    // TODO: Create a mock object of PostRepository
    @Mock
    private PostRepository postRepository;

    // TODO: Inject the mock into the PostServiceImpl instance
    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        // Reset the mock invocations before each test
        reset(postRepository);
    }

    @Test
    public void postService_createPost_shouldSavePostAndReturnCreatePostResponse(){
        // TODO: Given that I have a valid CreatePostRequest
        CreatePostRequest createPostRequest = CreatePostRequest.builder().title("Test title").content("Test content").build();

        // TODO: And I create a new Post instance and set its title and content
        Post mappedPost = Post.builder().title(createPostRequest.getTitle()).content(createPostRequest.getContent()).build();

        // TODO: And I mock the modelMapper's map method to map the createPostRequest to a Post object and return mappedPost.
        when(modelMapper.map(createPostRequest, Post.class)).thenReturn(mappedPost);

        when(postRepository.save(mappedPost)).thenReturn(mappedPost);

        // TODO: When I try to invoke the createPost method of the postService with the CreatePostRequest
        CreatePostResponse response = postService.createPost(createPostRequest);

        // TODO: And I assert that the response is not null
        assertThat(response).isNotNull();
        // TODO: And I assert that the response's title is equal to the CreatePostRequest's title
        assertThat(response.getTitle()).isEqualTo(createPostRequest.getTitle());
        // TODO: And I assert that the response's content is equal to the CreatePostRequest's content
        assertThat(response.getContent()).isEqualTo(createPostRequest.getContent());

        // TODO: Then I verify that the postRepository save method was invoked with the post
        verify(postRepository).save(mappedPost);

    }

    @Test
    void postService_getPostById_withValidPostId_shouldReturnGetPostResponse() {
        // TODO: Given that I have a mock Post instance
        Long postId = 1L;
        Post mockPost = Post.builder().id(postId).title("Test Title").content("Test Content").build();

        // TODO: And I Mock the behavior of postRepository.findById() to find & return the mockPost
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        // TODo: When I invoke the getPostById method of the postService with the postId
        GetPostResponse response = postService.getPostById(postId);

        // TODO: Then I assert that the response is not null
        assertThat(response).isNotNull();
        // TODO: I assert that the response's title is equal to the post's title
        assertThat(response.getTitle()).isEqualTo(mockPost.getTitle());
        // TODO: I assert that the response's content is equal to the post's content
        assertThat(response.getContent()).isEqualTo(mockPost.getContent());

        // TODO: I verify that postRepository.findById() was called with the correct postId
        verify(postRepository).findById(postId);

        // TODO: I verify that no other interactions occurred on the postRepository
        verifyNoMoreInteractions(postRepository);
    }

    @Test
    void postService_getPostById_withInvalidPostId_shouldThrowPostNotFoundException() {
        // TODO: Given that I have an invalid post id
        Long postId = 1L;

        // TODO: And I Mock the behavior of postRepository.findById() method to return an empty Optional
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When/Then
        // TODO: When I try to get a post with the invalid post ID
        // TODO: Then I assert that a PostNotFoundException is thrown
        assertThatThrownBy(() -> postService.getPostById(postId))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessage("Post could not be found");

        // TODO: And I verify that postRepository's findById() method was called
        verify(postRepository).findById(postId);

        // TODO: And I verify that no other interactions occurred on the postRepository
        verifyNoMoreInteractions(postRepository);
    }

    @Test
    public void postService_getAllPost_withValidPageNoAndPageSize_shouldReturnGetPostsResponse() {

        // TODO: Given that I have a list of mock posts
        Post post1 = Post.builder().title("Test Title 1").content("Test Content 1").build();
        Post post2 = Post.builder().title("Test Title 2").content("Test Content 2").build();
        List<Post> mockPosts = Arrays.asList(post1, post2);

        // TODO: And I create a mock Pageable object with pageNo 0, pageSize 10, and sorting by ID
        Pageable pageable = PageRequest.of(0, 10);

        // TODO: And I create a mock Page object containing the mock posts and using the mock pageable
        Page<Post> mockPage = new PageImpl<>(mockPosts, pageable, mockPosts.size());

        // TODO: And I mock the postRepository's findAll method to return the mock Page object
        when(postRepository.findAll(pageable)).thenReturn(mockPage);

        // TODO: When I try to get all posts with page number 0 and page size 10
        GetPostsResponse response = postService.getAllPost(0, 10);

        // TODO: Then I assert that the response is not null
        assertThat(response).isNotNull();
        // TODO: And I assert that the content list in the response has a size of 2
        assertThat(response.getContent()).hasSize(2);
        // TODO: And I assert that the page number in the response is equal to 0
        assertThat(response.getPageNo()).isEqualTo(0);
        // TODO: And I assert that the page size in the response is equal to 10
        assertThat(response.getPageSize()).isEqualTo(10);
        // TODO: And I assert that the total elements in the response is equal to 2
        assertThat(response.getTotalElements()).isEqualTo(2);
        // TODO: And I assert that the total pages in the response is equal to 1
        assertThat(response.getTotalPages()).isEqualTo(1);
        // TODO: And I assert that the "isLast" flag in the response is true
        assertThat(response.isLast()).isTrue();
        // TODO: Finally, I verify that the postRepository's findAll method was invoked with the pageable object
        verify(postRepository).findAll(pageable);

        // TODO: I assert the title and content of the first GetPostResponse object in the content list
        GetPostResponse getPostResponse1 = response.getContent().get(0);
        assertThat(getPostResponse1.getTitle()).isEqualTo("Test Title 1");
        assertThat(getPostResponse1.getContent()).isEqualTo("Test Content 1");

        // TODO: I assert the title and content of the second GetPostResponse object in the content list
        GetPostResponse getPostResponse2 = response.getContent().get(1);
        assertThat(getPostResponse2.getTitle()).isEqualTo("Test Title 2");
        assertThat(getPostResponse2.getContent()).isEqualTo("Test Content 2");
    }

    @Test
    public void getAllPost_withInvalidPageNoAndPageSize_shouldReturnEmptyGetPostsResponse() {
        // Given
        int pageNo = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> postPage = new PageImpl<>(Collections.emptyList(), pageable, 0L);
        when(postRepository.findAll(pageable)).thenReturn(postPage);

        // When
        GetPostsResponse response = postService.getAllPost(pageNo, pageSize);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getContent()).isEmpty();
        assertThat(response.getPageNo()).isEqualTo(pageNo);
        assertThat(response.getPageSize()).isEqualTo(pageSize);
        assertThat(response.getTotalElements()).isEqualTo(0L);
        assertThat(response.getTotalPages()).isEqualTo(0);
        assertThat(response.isLast()).isTrue();
    }

    @Test
    public void postService_UpdatePostById_withValidUpdatePostRequestAndPostId_shouldReturnUpdatePostResponse() {
        // TODO: Given that I have an updatePostRequest
        UpdatePostRequest updatePostRequest = UpdatePostRequest.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();

        // TODO: And I mock an existing post
        Long postId = 1L;
        Post existingPost = Post.builder().id(postId).title("Old Title").content("Old Content").build();

        // TODO: And I mock an updated post
        Post updatedPost = Post.builder().id(postId).title(updatePostRequest.getTitle()).content(updatePostRequest.getContent()).build();

        // TODO: And I mock the postRepository's findById() method to find & return the existing post
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));

        // TODO: And I mock the postRepository's save() method to save the existing post & return the updated post
        when(postRepository.save(existingPost)).thenReturn(updatedPost);

        // TODO: When I try to update the post with the updatePostRequest and postId
        UpdatePostResponse response = postService.updatePostById(updatePostRequest, postId);

        // TODO: Then I assert that the response is not null
        assertThat(response).isNotNull();

        // TODO: I assert that the response contains the updated title
        assertThat(response.getTitle()).isEqualTo(updatedPost.getTitle());

        // TODO: I assert that the response contains the updated content
        assertThat(response.getContent()).isEqualTo(updatedPost.getContent());

        // TODO: I verify that the post repository was invoked to find & retrieve the existing post
        verify(postRepository).findById(postId);

        // TODO: I verify that the post repository was invoked to save the updated post
        verify(postRepository).save(existingPost);

        // TODO: I verify that no other interactions should occur with the postRepository
        verifyNoMoreInteractions(postRepository);

    }

    @Test
    public void postService_updatePostById_withInValidPostId_shouldUpdatePostAndReturnUpdatePostResponse() {
        // TODO: Given that I have an invalid postId and an updatePostRequest
        Long postId = 1L;
        UpdatePostRequest updatePostRequest = UpdatePostRequest.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // TODO: When I try to update the post with the invalid postId
        // TODO: Then I assert that it throws a PostNotFoundException
        assertThatThrownBy(() -> postService.updatePostById(updatePostRequest, postId))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessage("Post could not be found");

    }

        @Test
    void postService_deletePostById_withValidPostId_shouldDeletePostAndReturnSuccessMessage() {
        // TODO: Given that I have a mock post
        Long postId = 1L;
        Post mockPost = Post.builder().id(postId).title("Test Title").content("Test Content").build();

        // TODO: And I mock the postRepository.findById() method to find & return the mockPost
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        // TODO: When I try to delete the post with the postId
        String result = postService.deletePostById(postId);

        // TODO: Then I assert that the success message "Post deleted" is returned
        assertThat(result).isEqualTo("Post deleted");

        // TODO: And I verify that the postRepository's delete() method was invoked to delete the post
        verify(postRepository).delete(mockPost);

        // TODO: And I verify that no other interactions occurred on the postRepository
        verifyNoMoreInteractions(postRepository);
    }

    @Test
    void postService_deletePostById_withInvalidPostId_shouldThrowPostNotFoundException() {
        // TODO: Given that I have an invalid postId
        Long postId = 1L;

        // TODO: And I mock the postRepository to return an empty Optional when its findById method is called with the invalid postId
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When/Then
        // TODO: When I try to delete a post with the invalid post ID
        // TODO: Then I assert that a PostNotFoundException is thrown
        assertThatThrownBy(() -> postService.deletePostById(postId))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessage("Post could not be deleted");

        // TODO: And I verify that postRepository's delete() method was not called
        verify(postRepository, never()).delete(any(Post.class));

        // TODO: And I verify that no other interactions occurred on the postRepository
        verifyNoMoreInteractions(postRepository);
    }
}