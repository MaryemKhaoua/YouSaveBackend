package com.example.yousavebackend.DTOs.Post;

import lombok.Data;

@Data
public class PostRequestDTO {
    private String content;
    private String createdBy;
}
