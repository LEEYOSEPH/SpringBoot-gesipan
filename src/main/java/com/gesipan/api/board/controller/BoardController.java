package com.gesipan.api.board.controller;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.request.BoardCreate;
import com.gesipan.api.board.response.BoardResponse;
import com.gesipan.api.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid BoardCreate params ) {
         boardService.write(params);
    }

    @GetMapping("/posts/{postId}")
    public BoardResponse get(@PathVariable(name = "postId") Long id) {
        BoardResponse response = boardService.get(id);
        return response;
    }

}
