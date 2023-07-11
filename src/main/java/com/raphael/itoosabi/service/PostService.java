package com.raphael.itoosabi.service;


import com.raphael.itoosabi.dto.request.CreatePostRequest;
import com.raphael.itoosabi.dto.request.UpdatePostRequest;
import com.raphael.itoosabi.dto.response.CreatePostResponse;
import com.raphael.itoosabi.dto.response.GetPostResponse;
import com.raphael.itoosabi.dto.response.GetPostsResponse;
import com.raphael.itoosabi.dto.response.UpdatePostResponse;

public interface PostService {

    CreatePostResponse createPost(CreatePostRequest createPostRequest);
    GetPostsResponse getAllPost(int pageNo, int pageSize);
    GetPostResponse getPostById(Long postId);
    UpdatePostResponse updatePostById(UpdatePostRequest updatePostRequest, Long postId);
    String deletePostById(Long postId);

}
