package com.raphael.itoosabi.controller;



import com.raphael.itoosabi.dto.request.CreatePostRequest;
import com.raphael.itoosabi.dto.request.UpdatePostRequest;
import com.raphael.itoosabi.dto.response.CreatePostResponse;
import com.raphael.itoosabi.dto.response.GetPostResponse;
import com.raphael.itoosabi.dto.response.GetPostsResponse;
import com.raphael.itoosabi.dto.response.UpdatePostResponse;
import com.raphael.itoosabi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest createPostRequest) {
        CreatePostResponse createdPost = postService.createPost(createPostRequest);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @Operation(
            description = "Get endpoint for post",
            summary = "This is a summary for post get endpoint",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "403")
            }
    )
    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable Long postId) {
//        GetPostResponse savedPost = postService.getPostById(postId);
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping("/")
    public ResponseEntity<GetPostsResponse> getPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        GetPostsResponse savedPosts = postService.getAllPost(pageNo, pageSize);
        return new ResponseEntity<>(savedPosts, HttpStatus.OK);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<UpdatePostResponse> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {
        UpdatePostResponse updatedPost = postService.updatePostById(updatePostRequest, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        String deletedPost = postService.deletePostById(postId);
        return new ResponseEntity<>(deletedPost, HttpStatus.OK);
    }
}
