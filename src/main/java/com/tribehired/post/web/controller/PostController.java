package com.tribehired.post.web.controller;

import com.tribehired.post.domain.request.CommentQuery;
import com.tribehired.post.domain.response.CommentDto;
import com.tribehired.post.domain.response.PostDto;
import com.tribehired.post.exception.NotFoundException;
import com.tribehired.post.service.PostService;
import com.tribehired.post.web.api.PostApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {
    private final PostService postService;

    @Override
    public PostDto topPost() {
        return postService.topPost().orElseThrow(() -> new NotFoundException("Top post not found"));
    }

    @Override
    public Page<CommentDto> searchComments(CommentQuery query) {
        log.info("{}", query);
        return postService.searchComments(query);
    }
}
