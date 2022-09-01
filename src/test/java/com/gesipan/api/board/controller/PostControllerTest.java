package com.gesipan.api.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.repository.BoardRepository;
import com.gesipan.api.board.request.BoardCreate;
import com.gesipan.api.board.request.BoardEdit;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.gesipan.api.board.domain.Board.builder;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    void clean() {
        boardRepository.deleteAll();

    }

    @Test
    @DisplayName("/posts 요청시 Hello World를 출력ㅎ한다")
    public void test() throws Exception {
        // given
        BoardCreate request = BoardCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 title 값은 핖수다.")
    public void test2() throws Exception {
        // 글제목
        // 글내용
        // 사용자
        //given
        // given
        BoardCreate request = BoardCreate.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다"))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해 주세요"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 db에 값이 저장된다.")
    public void test3() throws Exception {
        //given
        //given
        BoardCreate request = BoardCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1L, boardRepository.count());

        Board board = boardRepository.findAll().get(0);
        assertEquals("제목입니다.", board.getTitle());
        assertEquals("내용입니다.", board.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test4() throws  Exception{
        //given
        Board board = Board.builder()
                .title("123456789012345")
                .content("bar")
                .build();
        boardRepository.save(board);

        //expected
        mockMvc.perform(get("/posts/{postId}", board.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(board.getId()))
                .andExpect(jsonPath("$.title").value(board.getTitle()))
                .andExpect(jsonPath("$.content").value(board.getContent()))
                .andDo(print());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test5() throws  Exception{
        //given
        List<Board> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> builder()
                        .title("게시판 제목 " + i)
                        .content("게시글 내용 " + i)
                        .build())
                .collect(Collectors.toList());
        boardRepository.saveAll(requestPosts);

        //expected
        mockMvc.perform(get("/posts?page=1&size=10")
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("페이지를 0으로 조회")
    void test6() throws  Exception{
        //given
        List<Board> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> builder()
                        .title("게시판 제목 " + i)
                        .content("게시글 내용 " + i)
                        .build())
                .collect(Collectors.toList());
        boardRepository.saveAll(requestPosts);

        //expected
        mockMvc.perform(get("/posts?page=0&size=10")
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test7() throws  Exception{
        //given
        Board board = builder()
                .title("게시판 제목 ")
                .content("게시글 내용 ")
                .build();
        boardRepository.save(board);

        BoardEdit boardEdit = BoardEdit.builder()
                .title("수정한 제목")
                .build();

        //expected
        mockMvc.perform(patch("/posts/{postId}",board.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardEdit))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}