package com.gesipan.api.board.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardEditor {

    // 수정 가능한 필드들만 정리

    private final String title;
    private final String content;

    @Builder
    public BoardEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
