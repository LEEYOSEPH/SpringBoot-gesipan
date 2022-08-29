package com.gesipan.api.board.controller;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.request.BoardCreate;
import com.gesipan.api.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/posts")
    public Board post(@RequestBody @Valid BoardCreate params ) {
        // Case1. 저장한 데이터 Entity -> response로 응답하기
        return boardService.write(params);
    }
}
