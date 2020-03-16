package com.kawakawaryuryu.samplespringbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.stereotype.Component;

@Component
public class Step1ExecutionListener {

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("finish step1");
        return ExitStatus.STOPPED;
    }
}
