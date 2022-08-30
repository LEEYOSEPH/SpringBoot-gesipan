package com.gesipan.api.board.service;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.repository.BoardRepository;
import com.gesipan.api.board.request.BoardCreate;
import com.gesipan.api.board.response.BoardResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        BoardResponse response = boardService.get(requsetPost.getId());

        //then
        assertNotNull(response);
        assertEquals(1L,boardRepository.count());
        assertEquals("foo",response.getTitle());
        assertEquals("bar",response.getContent());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test3() {
        //given
        boardRepository.saveAll(List.of(
                Board.builder()
                    .title("foo1")
                    .content("bar1")
                    .build(),
                Board.builder()
                    .title("foo2")
                    .content("bar2")
                    .build()
        ));

        //when
        List<BoardResponse> posts = boardService.getList();

        //then
        assertEquals(2L,posts.size());

    }
}