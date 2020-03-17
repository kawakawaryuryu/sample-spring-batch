package com.kawakawaryuryu.samplespringbatch.config;

import com.kawakawaryuryu.samplespringbatch.listener.Step1ExecutionListener;
import com.kawakawaryuryu.samplespringbatch.step.reader.qiita.QiitaArticle;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemReader<QiitaArticle> qiitaArticleItemReader;

    private final ItemProcessor<QiitaArticle, QiitaArticle> qiitaItemProcessor;

    private final Step1ExecutionListener step1ExecutionListener;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       ItemReader<QiitaArticle> qiitaArticleItemReader,
                       ItemProcessor<QiitaArticle, QiitaArticle> qiitaItemProcessor,
                       Step1ExecutionListener step1ExecutionListener) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.qiitaArticleItemReader = qiitaArticleItemReader;
        this.qiitaItemProcessor = qiitaItemProcessor;
        this.step1ExecutionListener = step1ExecutionListener;
    }

    @Bean
    public Job job1(Step step1) {
        return jobBuilderFactory.get("job1")
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<QiitaArticle, QiitaArticle>chunk(5)
                .reader(qiitaArticleItemReader)
                .processor(qiitaItemProcessor)
                .writer(itemWriter())
                .listener(step1ExecutionListener)
                .build();
    }

    /**
     * QiitaArticleをtoStringしてoutput.txtに書き込んでいく
     */
    @Bean
    public ItemWriter<QiitaArticle> itemWriter() {
        return new FlatFileItemWriterBuilder<QiitaArticle>()
                .name("itemWriter")
                .resource(new FileSystemResource("output.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }
}
