package com.tribehired.post.converter;

import com.tribehired.post.domain.external.Comment;
import com.tribehired.post.domain.response.CommentDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter implements BaseConverter<Comment, CommentDto> {
    @Override
    public CommentDto convertTarget(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .email(comment.getEmail())
                .name(comment.getName())
                .body(comment.getBody())
                .build();
    }

    @SneakyThrows
    @Override
    public Comment convertSource(CommentDto dto) {
        throw new IllegalAccessException("This method is not allowed");
    }
}
