package com.kawakawaryuryu.samplespringbatch.step.reader.qiita;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@StepScope
public class QiitaApiItemReader implements ItemReader<QiitaArticle> {

    private final RestOperations restOperations;

    private final AtomicInteger atomicInteger;

    public QiitaApiItemReader(RestOperations restOperations) {
        this.restOperations = restOperations;
        this.atomicInteger = new AtomicInteger(0);
    }

    @Override
    public QiitaArticle read() {
        // 8回読み込んだら読み込み処理やめる
        if (atomicInteger.getAndIncrement() == 8) {
            return null;
        }
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
