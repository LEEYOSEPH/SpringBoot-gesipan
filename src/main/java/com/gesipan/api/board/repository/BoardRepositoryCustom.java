package com.gesipan.api.board.repository;

import com.gesipan.api.board.domain.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> getList(int page);
}
