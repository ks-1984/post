package com.tribehired.post.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private Long id;
    private Long postId;
    private String email;
    private String name;
    private String body;
}
