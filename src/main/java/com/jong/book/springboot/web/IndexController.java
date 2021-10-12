package com.jong.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // index
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 글등록
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
