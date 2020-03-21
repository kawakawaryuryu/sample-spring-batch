package com.kawakawaryuryu.samplespringbatch.step.chunk.qiita;

import com.kawakawaryuryu.samplespringbatch.infrastructure.qiita.QiitaArticleResponse;
import com.kawakawaryuryu.samplespringbatch.infrastructure.qiita.QiitaItemClient;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@StepScope
public class QiitaApiItemReader implements ItemReader<QiitaArticle> {

    private final QiitaItemClient qiitaItemClient;

    private final AtomicInteger atomicInteger;

    public QiitaApiItemReader(QiitaItemClient qiitaItemClient) {
        this.qiitaItemClient = qiitaItemClient;
        this.atomicInteger = new AtomicInteger(0);
    }

    @Override
    public QiitaArticle read() {
        // 8回読み込んだら読み込み処理やめる
        if (atomicInteger.getAndIncrement() == 8) {
            return null;
        }
        QiitaArticleResponse[] response = qiitaItemClient.getArticles().getBody();
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
