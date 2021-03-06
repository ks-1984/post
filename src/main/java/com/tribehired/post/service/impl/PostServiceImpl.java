package com.tribehired.post.service.impl;

import com.tribehired.post.converter.CommentConverter;
import com.tribehired.post.domain.external.Comment;
import com.tribehired.post.domain.request.CommentQuery;
import com.tribehired.post.domain.response.CommentDto;
import com.tribehired.post.domain.response.PostDto;
import com.tribehired.post.service.ExternalService;
import com.tribehired.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final ExternalService externalService;
    private final CommentConverter commentConverter;

    @Override
    public Optional<PostDto> topPost() {
        List<Comment> comments = externalService.comments();
        Map<Long, Long> map = comments.parallelStream()
                .collect(Collectors.groupingBy(Comment::getPostId, Collectors.counting()));
        return map.entrySet().parallelStream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(entry -> externalService.posts(entry.getKey())
                        .map(post -> PostDto.builder()
                                .postId(post.getId())
                                .postTitle(post.getTitle())
                                .postBody(post.getBody())
                                .totalNumberOfComments(Math.toIntExact(entry.getValue()))
                                .build())
                        .orElse(null));
    }

    @Override
    public Page<CommentDto> searchComments(CommentQuery query) {
        if (query.getPage() >= 0 && query.getSize() > 0) {
            List<Comment> list = externalService.comments().stream().filter(c -> {
                AtomicBoolean check = new AtomicBoolean(true);
                objectCheck(query.getId(), c.getId(), check);
                objectCheck(query.getPostId(), c.getPostId(), check);
                objectCheck(query.getEmail(), c.getEmail(), check);

                if (query.getNameSearchType() == CommentQuery.SearchType.EQUAL) {
                    objectCheck(query.getName(), c.getName(), check);
                } else if (query.getNameSearchType() == CommentQuery.SearchType.LIKE) {
                    likeCheck(query.getName(), c.getName(), check);
                }

                if (query.getBodySearchType() == CommentQuery.SearchType.EQUAL) {
                    objectCheck(query.getBody(), c.getBody(), check);
                } else if (query.getBodySearchType() == CommentQuery.SearchType.LIKE) {
                    likeCheck(query.getBody(), c.getBody(), check);
                }

                return check.get();
            }).collect(Collectors.toList());

            int start = query.getPage() * query.getSize();
            int end = start + query.getSize();
            if (end > list.size()) {
                end = list.size();
            }

            if (start <= end) {
                List<Comment> content = list.subList(start, end);
                if (!CollectionUtils.isEmpty(content)) {
                    return new PageImpl(commentConverter.convertTarget(content), PageRequest.of(start, query.getSize()), list.size());
                }
            }
        }

        return new PageImpl(new ArrayList<>());
    }

    private void objectCheck(Object source, Object target, AtomicBoolean check) {
        Optional.ofNullable(source).ifPresent(o-> {
            if (!o.equals(target)) {
                check.set(false);
            }
        });
    }

    private void likeCheck(String source, String target, AtomicBoolean check) {
        Optional.ofNullable(source).filter(s -> !StringUtils.isEmpty(s)).ifPresent(o-> {
            if (!target.contains(o)) {
                check.set(false);
            }
        });
    }
}
