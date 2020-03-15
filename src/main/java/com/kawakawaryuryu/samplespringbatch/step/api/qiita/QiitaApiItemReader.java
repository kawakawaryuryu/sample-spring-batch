package com.kawakawaryuryu.samplespringbatch.step.api.qiita;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

@Component
public class QiitaApiItemReader implements ItemReader<QiitaArticle> {

    private final RestOperations restOperations;

    public QiitaApiItemReader(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public QiitaArticle read() {
        URI uri = URI.create("https://qiita.com/api/v2/items?per_page=1");
        QiitaArticleResponse[] response = restOperations.getForObject(uri, QiitaArticleResponse[].class);
        return Arrays.stream(Optional.ofNullable(response).orElse(new QiitaArticleResponse[]{}))
                .findFirst()
                .map(res -> new QiitaArticle(
                        res.getId(),
                        res.getLikeCount(),
                        res.getTitle(),
                        res.getUrl()))
                .orElse(null);
    }
}
