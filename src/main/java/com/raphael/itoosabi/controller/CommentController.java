package com.raphael.itoosabi.controller;


import com.raphael.itoosabi.dto.request.CreateCommentRequest;
import com.raphael.itoosabi.dto.request.UpdateCommentRequest;
import com.raphael.itoosabi.dto.response.CreateCommentResponse;
import com.raphael.itoosabi.dto.response.GetCommentResponse;
import com.raphael.itoosabi.dto.response.UpdateCommentResponse;
import com.raphael.itoosabi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/post/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/{postId}")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long postId, @RequestBody CreateCommentRequest createCommentRequest) {
        CreateCommentResponse createdComment = commentService.createComment(postId, createCommentRequest);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }


    @GetMapping("/{postId}")

    public ResponseEntity<List<GetCommentResponse>> getCommentsByPostId(@PathVariable Long postId) {
        List<GetCommentResponse> getCommentResponseList = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(getCommentResponseList, HttpStatus.OK);
    }

    @GetMapping("/{postId}/{commentId}")
    public ResponseEntity<GetCommentResponse> getCommentById(@PathVariable Long postId, @PathVariable Long commentId) {
        GetCommentResponse savedComment = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        UpdateCommentResponse updatedComment = commentService.updateComment(postId, commentId, updateCommentRequest);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        String deletedComment = commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(deletedComment, HttpStatus.OK);
    }
}
