package com.kawakawaryuryu.samplespringbatch.step.processor.qiita;

import com.kawakawaryuryu.samplespringbatch.step.reader.qiita.QiitaArticle;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class QiitaItemProcessor implements ItemProcessor<QiitaArticle, QiitaArticle> {
    @Override
    public QiitaArticle process(QiitaArticle qiitaArticle) throws Exception {
        System.out.println(qiitaArticle.getId());
        System.out.println(qiitaArticle.getTitle());
        return qiitaArticle;
    }
}
