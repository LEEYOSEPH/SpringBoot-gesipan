package com.gesipan.api.board.service;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.domain.BoardEditor;
import com.gesipan.api.board.exception.BoardNotFound;
import com.gesipan.api.board.repository.BoardRepository;
import com.gesipan.api.board.request.BoardCreate;
import com.gesipan.api.board.request.BoardEdit;
import com.gesipan.api.board.request.BoardSearch;
import com.gesipan.api.board.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(BoardCreate boardCreate) {
        // postCreate -> Entity
        Board board = Board.builder()
                .title(boardCreate.getTitle())
                .content(boardCreate.getContent())
                .build();

        boardRepository.save(board);
    }

    public BoardResponse get(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    public List<BoardResponse> getList(BoardSearch boardSearch) {
        return boardRepository.getList(boardSearch).stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, BoardEdit boardEdit) {
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

        BoardEditor.BoardEditorBuilder editorBuilder = board.toEditor();

        BoardEditor boardEditor = editorBuilder.title(boardEdit.getTitle())
                .content(boardEdit.getContent())
                .build();

        board.edit(boardEditor);
    }

    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

        boardRepository.delete(board);

    }
}
