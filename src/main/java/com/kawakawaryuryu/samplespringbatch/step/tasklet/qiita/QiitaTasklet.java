package com.kawakawaryuryu.samplespringbatch.step.tasklet.qiita;

import com.kawakawaryuryu.samplespringbatch.infrastructure.qiita.QiitaArticleResponse;
import com.kawakawaryuryu.samplespringbatch.infrastructure.qiita.QiitaItemClient;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@StepScope
public class QiitaTasklet implements Tasklet {

    private final QiitaItemClient qiitaItemClient;

    public QiitaTasklet(QiitaItemClient qiitaItemClient) {
        this.qiitaItemClient = qiitaItemClient;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        QiitaArticleResponse[] responseBody = Optional.ofNullable(qiitaItemClient.getArticles().getBody())
                .orElse(new QiitaArticleResponse[]{});
        Arrays.stream(responseBody)
                .forEach(article -> System.out.println("id=" + article.getId() + ", title=" + article.getTitle()
                        + ", like_count=" + article.getLikeCount() + ", url=" + article.getUrl()));

        return RepeatStatus.FINISHED;
    }
}
