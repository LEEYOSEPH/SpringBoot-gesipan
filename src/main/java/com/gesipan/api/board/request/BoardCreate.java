package com.gesipan.api.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Setter @Getter
public class BoardCreate {

    @NotBlank(message = "타이틀을 입력해 주세요")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요")
    private String content;

    public BoardCreate() {
    }

    @Builder
    public BoardCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
