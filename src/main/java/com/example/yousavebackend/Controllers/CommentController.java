package com.example.yousavebackend.Controllers;

import com.example.yousavebackend.DTOs.Comment.CommentRequestDTO;
import com.example.yousavebackend.DTOs.Comment.CommentResponseDTO;
import com.example.yousavebackend.exceptions.CommentNotFoundException;
import com.example.yousavebackend.services.ICommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments() {
        List<CommentResponseDTO> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long id) {
        Optional<CommentResponseDTO> comment = commentService.getCommentById(id);
        return comment.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO createdComment = commentService.saveComment(commentRequestDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO updatedComment = commentService.updateComment(id, commentRequestDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
