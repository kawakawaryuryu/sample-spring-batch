package com.kawakawaryuryu.samplespringbatch.config;

import com.kawakawaryuryu.samplespringbatch.step.tasklet.qiita.QiitaTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final QiitaTasklet qiitaTasklet;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       QiitaTasklet qiitaTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.qiitaTasklet = qiitaTasklet;
    }

    @Bean
    public Job qiitaTaskletJob(Step qiitaTaskletStep) {
        return jobBuilderFactory.get("qiitaTaskletJob")
                .flow(qiitaTaskletStep)
                .end()
                .build();
    }

    @Bean
    public Step qiitaTaskletStep() {
        return stepBuilderFactory.get("qiitaTaskletStep")
                .tasklet(qiitaTasklet)
                .build();
    }
}
