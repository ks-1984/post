package com.tribehired.post.web.api;

import com.tribehired.post.domain.request.CommentQuery;
import com.tribehired.post.domain.response.CommentDto;
import com.tribehired.post.domain.response.PostDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Api(tags = {"Post"})
public interface PostApi {
    @ApiOperation(value = "Top Post", notes = "Get top post information",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful")
    })
    @GetMapping(value = "top-post", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PostDto topPost();

    @ApiOperation(value = "Search Comments", notes = "Search comments",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful")
    })
    @GetMapping(value = "comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Page<CommentDto> searchComments(@Valid CommentQuery query);
}
