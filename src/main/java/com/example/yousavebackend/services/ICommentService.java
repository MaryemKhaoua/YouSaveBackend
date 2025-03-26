package com.example.yousavebackend.services;

import com.example.yousavebackend.DTOs.Comment.CommentRequestDTO;
import com.example.yousavebackend.DTOs.Comment.CommentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<CommentResponseDTO> getAllComments();
    Optional<CommentResponseDTO> getCommentById(Long id);
    CommentResponseDTO saveComment(CommentRequestDTO commentRequestDTO);
    CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO);
    void deleteComment(Long id);
}