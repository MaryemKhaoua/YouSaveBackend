package com.example.yousavebackend.services.implementations;

import com.example.yousavebackend.DTOs.Comment.CommentRequestDTO;
import com.example.yousavebackend.DTOs.Comment.CommentResponseDTO;
import com.example.yousavebackend.entities.Comment;
import com.example.yousavebackend.entities.Post;
import com.example.yousavebackend.entities.User;
import com.example.yousavebackend.exceptions.CommentNotFoundException;
import com.example.yousavebackend.repositories.CommentRepository;
import com.example.yousavebackend.repositories.PostRepository;
import com.example.yousavebackend.repositories.UserRepository;
import com.example.yousavebackend.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommentResponseDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public CommentResponseDTO saveComment(CommentRequestDTO commentRequestDTO) {
        if (commentRequestDTO.getComment() == null || commentRequestDTO.getComment().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be null or empty.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(commentRequestDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + commentRequestDTO.getPostId()));

        Comment comment = new Comment();
        comment.setComment(commentRequestDTO.getComment());
        comment.setCreatedBy(user);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        return mapToDTO(savedComment);
    }

    @Override
    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with ID: " + id));

        existingComment.setComment(commentRequestDTO.getComment());
        Comment updatedComment = commentRepository.save(existingComment);

        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("Comment not found with ID: " + id);
        }
        commentRepository.deleteById(id);
    }

    private CommentResponseDTO mapToDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());

        User user = comment.getCreatedBy();
        String fullName = user.getFirstname() + " " + user.getLastname();

        dto.setCreatedBy(fullName);
        return dto;
    }

}
