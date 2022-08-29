package com.gesipan.api.board.service;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.repository.BoardRepository;
import com.gesipan.api.board.request.BoardCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void clean() {
        boardRepository.deleteAll();

    }

    @Test
    @DisplayName("글작성")
    void test1() {
        //given
        BoardCreate boardCreate = BoardCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        boardService.write(boardCreate);

        //then
        assertEquals(1L,boardRepository.count());
        Board board = boardRepository.findAll().get(0);
        assertEquals("제목입니다.",board.getTitle());
        assertEquals("내용입니다.",board.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        //given
        Board requsetPost = Board.builder()
                .title("foo")
                .content("bar")
                .build();
        boardRepository.save(requsetPost);

        //when
        Board board = boardService.get(requsetPost.getId());

        //then
        assertNotNull(board);
        assertEquals(1L,boardRepository.count());
        assertEquals("foo",board.getTitle());
        assertEquals("bar",board.getContent());
    }
}