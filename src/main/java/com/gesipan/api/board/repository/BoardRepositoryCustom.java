package com.gesipan.api.board.repository;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.request.BoardSearch;

import java.util.List;

public interface BoardRepositoryCustom  {

    List<Board> getList(BoardSearch boardSearch);
}
