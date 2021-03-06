package com.tribehired.post.web.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Profile(value = {"dev"})
@Controller
public class SwaggerController {
    @GetMapping(value = "/")
    public String index() {
        return "redirect:/swagger-ui.html";
    }
}
