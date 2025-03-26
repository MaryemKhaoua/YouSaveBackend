package com.example.yousavebackend.services.implementations;

import com.example.yousavebackend.DTOs.Post.PostRequestDTO;
import com.example.yousavebackend.DTOs.Post.PostResponseDTO;
import com.example.yousavebackend.entities.Post;
import com.example.yousavebackend.entities.User;
import com.example.yousavebackend.exceptions.PostAlreadyExistsException;
import com.example.yousavebackend.exceptions.PostNotFoundException;
import com.example.yousavebackend.repositories.PostRepository;
import com.example.yousavebackend.repositories.UserRepository;
import com.example.yousavebackend.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<PostResponseDTO> getPostById(Long id) {
        return postRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public PostResponseDTO savePost(PostRequestDTO postRequestDTO) {
        if (postRequestDTO.getContent() == null || postRequestDTO.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be null or empty.");
        }

        boolean postExists = postRepository.findAll().stream()
                .anyMatch(existingPost -> existingPost.getContent() != null &&
                        existingPost.getContent().equalsIgnoreCase(postRequestDTO.getContent()));

        if (postExists) {
            throw new PostAlreadyExistsException("Post with the same content already exists.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setContent(postRequestDTO.getContent());
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        return mapToDTO(savedPost);
    }


    @Override
    public PostResponseDTO updatePost(Long id, PostRequestDTO postRequestDTO) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + id));

        existingPost.setContent(postRequestDTO.getContent());
        Post updatedPost = postRepository.save(existingPost);

        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException("Post not found with ID: " + id);
        }
        postRepository.deleteById(id);
    }

    private PostResponseDTO mapToDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        User user = post.getUser();
        String fullName = user.getFirstname() + " " + user.getLastname();
        dto.setCreatedBy(fullName);
        dto.setCreatedAt(post.getCreatedAt());
        return dto;
    }
}
