package com.kawakawaryuryu.samplespringbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class ParallelJobListener implements JobExecutionListener {

  private final ThreadPoolTaskExecutor executor;

  @Override
  public void beforeJob(JobExecution jobExecution) {

  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      executor.shutdown();
    }
  }
}
