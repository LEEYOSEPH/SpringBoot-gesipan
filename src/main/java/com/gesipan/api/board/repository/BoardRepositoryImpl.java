package com.gesipan.api.board.repository;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.domain.QBoard;
import com.gesipan.api.board.request.BoardSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gesipan.api.board.domain.QBoard.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom  {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> getList(BoardSearch boardSearch) {
        return jpaQueryFactory.selectFrom(board)
                .limit(boardSearch.getSize())
                .offset(boardSearch.getOffset())
                .orderBy(board.id.desc())
                .fetch();
    }
}
