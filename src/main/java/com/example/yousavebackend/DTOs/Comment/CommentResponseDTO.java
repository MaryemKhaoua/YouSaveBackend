package com.example.yousavebackend.DTOs.Comment;

import lombok.Data;

@Data
public class CommentResponseDTO {
    private long id;
    private String comment;
    private String createdBy;
}
