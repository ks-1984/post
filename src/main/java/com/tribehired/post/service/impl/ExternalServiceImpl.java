package com.tribehired.post.service.impl;

import com.tribehired.post.domain.external.Comment;
import com.tribehired.post.domain.external.Post;
import com.tribehired.post.service.ExternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {
    private static final String POSTS = "https://jsonplaceholder.typicode.com/posts{0}";
    private static final String COMMENTS = "https://jsonplaceholder.typicode.com/comments";

    private final RestTemplate restTemplate;

    @Override
    public List<Comment> comments() {
        try {
            return Arrays.asList(restTemplate.getForObject(COMMENTS, Comment[].class));
        } catch (Exception e) {
            log.error("Failed to call comments end point");
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Post> posts(Long id) {
        try {
            return Optional.ofNullable(
                    restTemplate.getForObject(MessageFormat.format(POSTS, "/" + id), Post.class)
            );
        } catch (Exception e) {
            log.error("Failed to call single post end point");
            return Optional.empty();
        }
    }

    @Override
    public List<Post> posts() {
        try {
            return Arrays.asList(restTemplate.getForObject(MessageFormat.format(POSTS, ""), Post[].class));
        } catch (Exception e) {
            log.error("Failed to call posts end point");
            return new ArrayList<>();
        }
    }
}
