package com.raphael.itoosabi.service.impl;


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
import com.raphael.itoosabi.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public CreateCommentResponse createComment(Long postId, CreateCommentRequest createCommentRequest) {
        // TODO: 1. get the post
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with associated comment not found"));
        // TODO: 2. create the comment
        Comment comment = modelMapper.map(createCommentRequest, Comment.class);
        // TODO: 3. Link the comment to the post
        comment.setPost(post);
        // TODO: 4. save the comment in the database
        commentRepository.save(comment);


        // TODO: 5. add the comment to the list of comments in the post
        post.getCommentList().add(comment);
        // TODO: 6. save the post in the database
        postRepository.save(post);


        // TODO: 7. send a response to the user
        return CreateCommentResponse.builder().content(comment.getContent()).build();
    }

    @Override
    public GetCommentResponse getCommentById(Long postId, Long commentId) {
        // TODO: Retrieve the post from the post repository using the given post ID or throw an exception if not found
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with associated comment not found"));
        // TODO: Retrieve the comment from the comment repository using the given comment ID or throw an exception if not found
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with associate post not found"));
        // TODO: Check if the given post ID matches the post ID associated with the comment, and throw an exception if they are not the same
        if (!post.getId().equals(comment.getPost().getId())) throw new CommentNotFoundException("This comment does not belong to a post");
        // TODO: Create and return a GetCommentResponse object with the content of the retrieved comment
        return GetCommentResponse.builder().content(comment.getContent()).build();
    }

    @Override
    public List<GetCommentResponse> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        // TODO: Retrieve all comments associated with the specific post ID
        List<Comment> comments = commentRepository.findAllByPostId(post.getId());
        // TODO: Map each Comment entity to a GetCommentResponse and collect them into a list
        List<GetCommentResponse> listOfGetCommentResponse = comments.stream()
                .map(comment -> GetCommentResponse.builder()
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());

        // TODO: Return the list of comments
        return listOfGetCommentResponse;
    }

    @Override
    public UpdateCommentResponse updateComment(Long postId, Long commentId, UpdateCommentRequest updateCommentRequest) {
        // TODO: 1. get the post
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with associated comment not found"));
        // TODO: 2. get the comment
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with associate post not found"));
        // TODO: 3. check if the postId from the db is the same as the postId whose comment we want to update
        if (!post.getId().equals(comment.getPost().getId())) throw new CommentNotFoundException("This comment does not belong to a post");
        // TODO: 4. copy the updateCommentRequest to the comment in the database
        modelMapper.map(updateCommentRequest, comment);
        // TODO: 5. save the updated comment in the database
        Comment updatedComment = commentRepository.save(comment);


        // TODO: 6. check the list of comments in the post if there is any comment that is equal to the updated comment
        post.getCommentList().removeIf(foundComment -> foundComment.getId().equals(updatedComment.getId()));
        // TODO: 7. add the updated comment to the list of comments in the post
        post.getCommentList().add(updatedComment);
        // TODO: 8. save the post in the database
        postRepository.save(post);


        // TODO: return a response to the user
        return UpdateCommentResponse.builder()
                .content(updatedComment.getContent())
                .build();
    }

    @Override
    public String deleteComment(Long postId, Long commentId) {
        // TODO: 1. get the post from the db
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with associated comment not found"));
        // TODO: 2. get the comment from the db
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with associated post not found"));
        // TODO: 3. check if the postId from the db is the same as the postId whose comment we want to delete
        if (!post.getId().equals(comment.getPost().getId())) throw new CommentNotFoundException("This comment does not belong to a post");
        // TODO: delete the comment
        commentRepository.delete(comment);


        // TODO: get the list of comments from the post
        // TODO: remove the deleted comment from the list of comments in the post
        // TODO: save the post


        // TODO: return a response
        return "Comment deleted successfully";
    }

}
