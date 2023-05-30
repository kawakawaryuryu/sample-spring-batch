package com.kawakawaryuryu.samplespringbatch.config;

import com.kawakawaryuryu.samplespringbatch.step.tasklet.HelloWorldTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@EnableBatchProcessing
@RequiredArgsConstructor
public class ParallelJob {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job helloWorldParallelJob(
      Flow helloWorldFlow1,
      Flow helloWorldFlow2,
      Flow helloWorldFlow3,
      ThreadPoolTaskExecutor executor) {
    return jobBuilderFactory.get("helloWorldParallelJob")
        .start(helloWorldFlow1)
        .split(executor)
        .add(helloWorldFlow2, helloWorldFlow3)
        .end()
        .listener(new JobExecutionListener() {
          @Override
          public void beforeJob(JobExecution jobExecution) {

          }

          @Override
          public void afterJob(JobExecution jobExecution) {
            executor.shutdown();
          }
        })
        .build();
  }

  @Bean
  public Flow helloWorldFlow1(Step helloWorldStep) {
    return new FlowBuilder<SimpleFlow>("flow1")
        .start(helloWorldStep)
        .build();
  }

  @Bean
  public Flow helloWorldFlow2(Step helloWorldStep) {
    return new FlowBuilder<SimpleFlow>("flow2")
        .start(helloWorldStep)
        .build();
  }

  @Bean
  public Flow helloWorldFlow3(Step helloWorldStep) {
    return new FlowBuilder<SimpleFlow>("flow3")
        .start(helloWorldStep)
        .build();
  }

  @Bean
  public Step helloWorldStep(HelloWorldTasklet helloWorldTasklet) {
    return stepBuilderFactory.get("helloWorldStep")
        .tasklet(helloWorldTasklet)
        .build();
  }

  @Bean
  public ThreadPoolTaskExecutor executor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(2);
    taskExecutor.setMaxPoolSize(2);
    return taskExecutor;
  }
}
