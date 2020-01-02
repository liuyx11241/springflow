package com.github.liuyx11241.springflow.config;

import com.github.liuyx11241.springflow.stereotype.EnableSpecificFlow;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(SpecificFlowDecider.class)
@ConditionalOnWebApplication
@ConditionalOnBean(annotation = {EnableSpecificFlow.class})
public class SpecificFlowDeciderConfig {

    @Bean
    public SpecificFlowDecider defaultSpecificDecider() {
        return () -> {
            throw new UnsupportedOperationException(String.format("Please provide a bean of %s", SpecificFlowDecider.class));
        };
    }
}
