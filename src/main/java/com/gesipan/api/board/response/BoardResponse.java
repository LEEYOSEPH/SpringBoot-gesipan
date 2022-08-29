package com.gesipan.api.board.response;

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

    @Builder
    public BoardResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0,Math.min(title.length(),10));
        this.content = content;
    }
}
