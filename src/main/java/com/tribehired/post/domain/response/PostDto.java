package com.tribehired.post.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    @JsonProperty("post_id")
    private Long postId;

    @JsonProperty("post_title")
    private String postTitle;

    @JsonProperty("post_body")
    private String postBody;

    @JsonProperty("total_number_of_comments")
    private Integer totalNumberOfComments;
}
