package com.gesipan.api.board.response;

import com.gesipan.api.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 서비스 정책에 맞는 응답 클래스 만들자
 * */
@Getter
public class BoardResponse {

    private final Long id;
    private final String title;
    private final String content;

    //생성자 오버로딩
    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }

    @Builder
    public BoardResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0,Math.min(title.length(),10));
        this.content = content;
    }
}
