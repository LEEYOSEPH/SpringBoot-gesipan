package com.gesipan.api.board.repository;

import com.gesipan.api.board.domain.Board;
import com.gesipan.api.board.domain.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom  {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> getList(int page) {
        return jpaQueryFactory.selectFrom(QBoard.board)
                .limit(10)
                .offset((long)(page - 1) * 10)
                .fetch();
    }
}
