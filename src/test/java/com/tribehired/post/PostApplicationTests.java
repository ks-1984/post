package com.tribehired.post;

import com.tribehired.post.domain.external.Post;
import com.tribehired.post.domain.request.CommentQuery;
import com.tribehired.post.service.ExternalService;
import com.tribehired.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class PostApplicationTests {
	@Autowired
	private ExternalService externalService;

	@Autowired
	private PostService postService;

	@Test
	void contextLoads() {
	}

	@Test
	void externalServiceTest() {
		Assert.noNullElements(externalService.comments(), "Failed to get comments");
		List<Post> posts = externalService.posts();
		Assert.noNullElements(posts, "Failed to get comments");
		Optional<Post> post = externalService.posts(posts.get(0).getId());
		Assert.isTrue(post.isPresent(), "Failed to get single post");
		Assert.isTrue(post.get().getId().equals(posts.get(0).getId()), "Invalid post");
	}

	@Test
	void postServiceTest() {
		Assert.isTrue(postService.topPost().isPresent(), "Top post not found");
		Assert.isTrue(postService.searchComments(
				CommentQuery.builder()
						.page(0)
						.size(1)
						.build()
		).getContent().get(0).getId().equals(1L), "Comment first record query failed");
		Assert.isTrue(postService.searchComments(
				CommentQuery.builder()
						.page(1)
						.size(1)
						.build()
		).getContent().get(0).getId().equals(2L), "Comment second record query failed");
		Assert.isTrue(postService.searchComments(
				CommentQuery.builder()
						.id(1L)
						.build()
		).getContent().get(0).getId().equals(1L), "Id query failed");
		Assert.isTrue(postService.searchComments(
				CommentQuery.builder()
						.email("Eliseo@gardner.biz")
						.build()
		).getContent().get(0).getEmail().equals("Eliseo@gardner.biz"), "email query failed");
		Assert.isTrue(postService.searchComments(
				CommentQuery.builder()
						.name("quam laborum")
						.nameSearchType(CommentQuery.SearchType.LIKE)
						.build()
		).getContent().get(0).getName().contains("quam laborum"), "Name LIKE query failed");
	}
}
