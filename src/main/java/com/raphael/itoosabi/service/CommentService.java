package com.raphael.itoosabi.service;




import com.raphael.itoosabi.dto.request.CreateCommentRequest;
import com.raphael.itoosabi.dto.request.UpdateCommentRequest;
import com.raphael.itoosabi.dto.response.CreateCommentResponse;
import com.raphael.itoosabi.dto.response.GetCommentResponse;
import com.raphael.itoosabi.dto.response.UpdateCommentResponse;

import java.util.List;

public interface CommentService {
    CreateCommentResponse createComment(Long postId, CreateCommentRequest createCommentRequest);
    List<GetCommentResponse> getCommentsByPostId(Long postId);

    GetCommentResponse getCommentById(Long postId, Long commentId);

    UpdateCommentResponse updateComment(Long postId, Long commentId, UpdateCommentRequest updateCommentRequest);

    String deleteComment(Long postId, Long commentId);


}
