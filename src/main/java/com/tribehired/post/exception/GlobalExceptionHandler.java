package com.tribehired.post.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public void notFoundException(NotFoundException ex, HttpServletResponse response) throws IOException {
        log.info("error: {}", ex.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
