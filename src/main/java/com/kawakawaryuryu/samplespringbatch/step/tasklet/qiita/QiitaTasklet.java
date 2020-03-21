package com.kawakawaryuryu.samplespringbatch.step.tasklet.qiita;

import com.kawakawaryuryu.samplespringbatch.infrastructure.qiita.QiitaArticleResponse;
import com.kawakawaryuryu.samplespringbatch.infrastructure.qiita.QiitaItemClient;
import com.kawakawaryuryu.samplespringbatch.step.chunk.qiita.QiitaArticle;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QiitaTasklet implements Tasklet {

    private final QiitaItemClient qiitaItemClient;

    public QiitaTasklet(QiitaItemClient qiitaItemClient) {
        this.qiitaItemClient = qiitaItemClient;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<QiitaArticle> articles = Arrays.stream(Optional.ofNullable(qiitaItemClient.getArticles().getBody()).orElse(new QiitaArticleResponse[]{}))
                .map(article -> new QiitaArticle(
                        article.getId(),
                        article.getLikeCount(),
                        article.getTitle(),
                        article.getUrl()))
                .collect(Collectors.toList());

        articles.forEach(System.out::println);

        return RepeatStatus.FINISHED;
    }
}
