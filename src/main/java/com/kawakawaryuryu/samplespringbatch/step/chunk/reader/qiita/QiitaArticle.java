package com.kawakawaryuryu.samplespringbatch.step.chunk.reader.qiita;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QiitaArticle {
    private String id;

    private int likeCount;

    private String title;

    private String url;
}
