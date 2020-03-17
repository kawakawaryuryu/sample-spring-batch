package com.kawakawaryuryu.samplespringbatch;

import com.kawakawaryuryu.samplespringbatch.config.BatchConfig;
import com.kawakawaryuryu.samplespringbatch.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.runner.RunWith;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBatchTest
@EnableAutoConfiguration
@ComponentScan
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        BatchConfig.class,
        WebConfig.class
})
class SampleSpringBatchApplicationTests {

    @Test
    void contextLoads() {
    }

}
