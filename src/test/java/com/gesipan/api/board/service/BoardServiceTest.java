package com.gesipan.api.board.service;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.repository.BoardRepository;
import com.gesipan.api.board.request.BoardCreate;
import com.gesipan.api.board.request.BoardEdit;
import com.gesipan.api.board.request.BoardSearch;
import com.gesipan.api.board.response.BoardResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.gesipan.api.board.domain.Board.*;
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
        Board requsetPost = builder()
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
    @DisplayName("글 1페이지 조회")
    void test3() {
        //given
        List<Board> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> builder()
                        .title("게시판 제목 " + i)
                        .content("게시글 내용 " + i)
                        .build())
                .collect(Collectors.toList());
        boardRepository.saveAll(requestPosts);

        BoardSearch boardSearch = BoardSearch.builder()
                .page(1)
                .build();

        //when
        List<BoardResponse> posts = boardService.getList(boardSearch);

        //then
        assertEquals(10L,posts.size());
        assertEquals("게시판 제목 19",posts.get(0).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        //given

        Board board = builder()
                .title("게시판 제목 ")
                .content("게시글 내용 ")
                .build();
        boardRepository.save(board);

        BoardEdit boardEdit = BoardEdit.builder()
                .title("수정한 제목")
                .build();


        //when
        boardService.edit(board.getId(),boardEdit);

        //then
        Board changedBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재 하지 않습니다. id=" + board.getId()));
        assertEquals("수정한 제목",changedBoard);
    }

    @Test
    @DisplayName("글 제목 수정")
    void test5() {
        //given
        Board board = builder()
                .title("게시판 제목 ")
                .content("게시글 내용 ")
                .build();
        boardRepository.save(board);

        BoardEdit boardEdit = BoardEdit.builder()
                .title("수정한 제목")
                .build();


        //when
        boardService.edit(board.getId(),boardEdit);

        //then
        Board changedBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재 하지 않습니다. id=" + board.getId()));
        assertEquals("수정한 제목",changedBoard.getTitle());
    }
}