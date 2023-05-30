package com.kawakawaryuryu.samplespringbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfig {

//  @Bean
  public ThreadPoolTaskExecutor executor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(2);
    taskExecutor.setMaxPoolSize(2);
//    SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
//    taskExecutor.setConcurrencyLimit(2);
    return taskExecutor;
  }
}
