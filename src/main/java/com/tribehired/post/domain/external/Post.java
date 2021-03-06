package com.tribehired.post.domain.external;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
