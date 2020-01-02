package com.github.liuyx11241.springflow.showcase;

import com.github.liuyx11241.springflow.config.SpecificFlowBeanConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class TestInterfaceConfig extends SpecificFlowBeanConfig<TestInterface> {
    @Override
    @Bean
    @Primary
    @RequestScope
    public TestInterface primaryBean() {
        return super.specificBean(TestInterface.class);
    }
}
