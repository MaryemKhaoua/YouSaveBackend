package com.example.yousavebackend.services;

import com.example.yousavebackend.DTOs.Post.PostResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<PostResponseDTO> getAllPosts();
    Optional<PostResponseDTO> getPostById(Long id);
    PostResponseDTO savePost(PostResponseDTO postRequestDTO);
    PostResponseDTO updatePost(Long id, PostResponseDTO postRequestDTO);
    void deletePost(Long id);
}