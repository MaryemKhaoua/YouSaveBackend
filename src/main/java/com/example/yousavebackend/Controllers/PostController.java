package com.example.yousavebackend.Controllers;

import com.example.yousavebackend.DTOs.Post.PostRequestDTO;
import com.example.yousavebackend.DTOs.Post.PostResponseDTO;
import com.example.yousavebackend.exceptions.PostAlreadyExistsException;
import com.example.yousavebackend.exceptions.PostNotFoundException;
import com.example.yousavebackend.services.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        Optional<PostResponseDTO> post = postService.getPostById(id);
        return post.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
        try {
            PostResponseDTO createdPost = postService.savePost(postRequestDTO);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (PostAlreadyExistsException ex) {
            throw new PostAlreadyExistsException(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO updatedPost = postService.updatePost(id, postRequestDTO);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
