package com.tribehired.post.domain.external;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Comment {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
