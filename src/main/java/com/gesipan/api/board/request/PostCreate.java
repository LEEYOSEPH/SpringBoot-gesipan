package com.gesipan.api.board.request;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class PostCreate {

    public String title;
    public String content;
}
