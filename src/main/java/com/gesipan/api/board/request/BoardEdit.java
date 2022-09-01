package com.gesipan.api.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
public class BoardEdit {

    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    @Builder
    public BoardEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
