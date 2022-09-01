package com.gesipan.api.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class BoardSearch {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page;
    @Builder.Default
    private Integer size;

    public long getOffset() {
        return (long) (Math.max(1,page) - 1) * Math.min(size,MAX_SIZE);
    }

}
