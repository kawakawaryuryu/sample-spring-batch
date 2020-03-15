package com.kawakawaryuryu.samplespringbatch.step.api.qiita;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QiitaArticleResponse {

    private String id;

    @JsonProperty("like_count")
    private int likeCount;

    private String title;

    private String url;
}

