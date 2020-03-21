package com.kawakawaryuryu.samplespringbatch.step.chunk.qiita;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class QiitaArticle {
    private String id;

    private int likeCount;

    private String title;

    private String url;
}
