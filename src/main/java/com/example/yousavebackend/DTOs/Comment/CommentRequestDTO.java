package com.example.yousavebackend.DTOs.Comment;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private String comment;
    private Long postId;
    private String createdBy;
}
