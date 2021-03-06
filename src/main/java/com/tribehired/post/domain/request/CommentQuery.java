package com.tribehired.post.domain.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentQuery {
    private Long id;
    private Long postId;
    private String email;
    private String name;
    private String body;
    private SearchType nameSearchType;
    private SearchType bodySearchType;

    @Min(value = 0, message = "page must be greater than or equal to 0")
    private int page = 0;

    @Min(value = 1, message = "size must be greater than or equal to 1")
    private int size = 100;

    public enum SearchType {
        EQUAL, LIKE
    }
}
