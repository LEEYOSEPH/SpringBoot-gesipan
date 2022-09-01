package com.gesipan.api.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public BoardEditor.BoardEditorBuilder toEditor() {
         return BoardEditor.builder()
                .title(title)
                .content(content);
    }

    public void edit(BoardEditor boardEditor) {
        this.title = boardEditor.getTitle();
        this.content = boardEditor.getContent();
    }

}
