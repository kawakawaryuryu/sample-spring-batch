package com.kawakawaryuryu.samplespringbatch.config;

import com.kawakawaryuryu.samplespringbatch.step.api.qiita.QiitaArticle;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemReader<QiitaArticle> qiitaArticleItemReader;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       ItemReader<QiitaArticle> qiitaArticleItemReader) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.qiitaArticleItemReader = qiitaArticleItemReader;
    }

    @Bean
    public Job job(Step step1) {
        return jobBuilderFactory.get("job1")
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(ItemProcessor processor, ItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .chunk(5)
                .reader(qiitaArticleItemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
