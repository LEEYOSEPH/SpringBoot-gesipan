package com.gesipan.api.board.controller;

import com.gesipan.api.board.request.BoardCreate;
import com.gesipan.api.board.request.BoardEdit;
import com.gesipan.api.board.request.BoardSearch;
import com.gesipan.api.board.response.BoardResponse;
import com.gesipan.api.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public BoardResponse get(@PathVariable Long postId) {
        return boardService.get(postId);
    }



    @GetMapping("/posts")
    public List<BoardResponse> getList(@ModelAttribute BoardSearch boardSearch ) {
        return boardService.getList(boardSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long boardId, @RequestBody @Valid BoardEdit request) {
        boardService.edit(boardId,request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }

}
