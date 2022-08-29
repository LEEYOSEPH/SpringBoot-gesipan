package com.gesipan.api.board.service;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.repository.BoardRepository;
import com.gesipan.api.board.request.BoardCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board write(BoardCreate boardCreate) {
        // postCreate -> Entity
        Board board = Board.builder()
                .title(boardCreate.getTitle())
                .content(boardCreate.getContent())
                .build();

       return boardRepository.save(board);
    }
}
