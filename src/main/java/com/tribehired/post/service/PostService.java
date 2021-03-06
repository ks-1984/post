package com.tribehired.post.service;

import com.tribehired.post.domain.request.CommentQuery;
import com.tribehired.post.domain.response.CommentDto;
import com.tribehired.post.domain.response.PostDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {
    Optional<PostDto> topPost();
    Page<CommentDto> searchComments(CommentQuery query);
}
