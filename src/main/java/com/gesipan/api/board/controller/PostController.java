package com.gesipan.api.board.controller;

import com.gesipan.api.board.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostController {

    @PostMapping("/posts")
    public String post(@RequestBody PostCreate params) throws Exception {

        log.info("params={}",params.toString());

        String title = params.getTitle();
        if (title == null || title.equals("")) {
            throw new Exception("타이틀 값이 없음");
        }

        String content = params.getContent();
        if (content == null || content.equals("")) {
            //error
        }

        return "hello World";
    }
}
