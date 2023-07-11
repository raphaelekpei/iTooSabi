package com.raphael.itoosabi.service.impl;



import com.raphael.itoosabi.data.models.Post;
import com.raphael.itoosabi.data.repository.PostRepository;
import com.raphael.itoosabi.dto.request.CreatePostRequest;
import com.raphael.itoosabi.dto.request.UpdatePostRequest;
import com.raphael.itoosabi.dto.response.CreatePostResponse;
import com.raphael.itoosabi.dto.response.GetPostResponse;
import com.raphael.itoosabi.dto.response.GetPostsResponse;
import com.raphael.itoosabi.dto.response.UpdatePostResponse;
import com.raphael.itoosabi.exceptions.PostNotFoundException;
import com.raphael.itoosabi.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        if (modelMapper == null) throw new IllegalStateException("ModelMapper has not been properly initialized.");
        Post post = modelMapper.map(createPostRequest, Post.class);
        postRepository.save(post);
        return CreatePostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    @Override
    public GetPostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post could not be found"));
        return GetPostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    @Override
    public GetPostsResponse getAllPost(int pageNo, int pageSize) {
        // TODO: Create a pageable object to define the requested page number and size
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        // TODO: Retrieve a page of Post entities from the repository based on the pageable object
        Page<Post> posts = postRepository.findAll(pageable);
        // TODO: Extract all the content from the page
        List<Post> postList = posts.getContent();

        // TODO: Map each Post entity to a GetPostResponse and collect them into a list
        List<GetPostResponse> getPostResponseList = postList.stream()
                .map(post -> GetPostResponse.builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());

        // TODO: Create & return a CustomPostResponse object to hold the paginated data
        return GetPostsResponse.builder()
                .content(getPostResponseList)
                .pageNo(posts.getNumber())  // The pageable object gives you all these data so that you don't have to write any logic to implement any of them
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
    }

    @Override
    public UpdatePostResponse updatePostById(UpdatePostRequest updatePostRequest, Long postId) {
        // TODO: Retrieve the existing post from the repository
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post could not be found"));
        // TODO: Update the post with the new values
        modelMapper.map(updatePostRequest, post);
        // TODO: Save the updated post
        Post updatedPost = postRepository.save(post);
        // TODO: Create and return the response
        return UpdatePostResponse.builder()
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .build();
    }

    @Override
    public String deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post could not be deleted"));
        postRepository.delete(post);
        return "Post deleted";
    }

}

/*

GetAllPostsMethod

listOfPost.stream() - This converts the listOfPost into a stream.
A stream is a sequence of elements that can be processed in parallel or sequentially.

.map(post -> GetPostResponse.builder() ... ) -
The map operation applies a function to each element of the stream and returns a new stream consisting of the results.
In this case, it takes each Post object in the stream and maps it to a GetPostResponse object using the provided lambda expression.

.title(post.getTitle()) - This sets the title attribute of the GetPostResponse object with the title attribute of the corresponding Post object.

.content(post.getContent()) - This sets the content attribute of the GetPostResponse object with the content attribute of the corresponding Post object.

.build() - This builds the GetPostResponse object using the values set for the title and content attributes.

.collect(Collectors.toList()) - This collects the elements of the stream into a new list.
The Collectors.toList() collector is used here to create a List<GetPostResponse> from the stream of GetPostResponse objects.

In summary, this line of code creates a new list (listOfGetPostResponse) by
mapping each Post object in listOfPost to a corresponding GetPostResponse object,
setting the title and content attributes, and
collecting the results into the new list.

 */