package com.tribehired.post.service;

import com.tribehired.post.domain.external.Comment;
import com.tribehired.post.domain.external.Post;

import java.util.List;
import java.util.Optional;

public interface ExternalService {
    List<Comment> comments();
    Optional<Post> posts(Long id);
    List<Post> posts();
}
