package com.example.yousavebackend.DTOs.Post;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class PostResponseDTO {
    private Long id;
    @NotBlank(message = "Content cannot be blank")
    private String content;
    private String createdBy;

}
