package com.github.liuyx11241.springflow.config;

import com.github.liuyx11241.springflow.stereotype.EnableSpecificFlow;
import com.github.liuyx11241.springflow.stereotype.SpecificFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

import java.util.Map;

@ConditionalOnWebApplication
@ConditionalOnBean(annotation = {EnableSpecificFlow.class})
public abstract class SpecificFlowBeanConfig<T> {
    @Autowired
    SpecificFlowBeanProcessor beanProcessor;

    @Autowired
    SpecificFlowDecider specificFlowDecider;

    private Map<Class<? extends SpecificFlow>, T> specificBeans;

    protected T specificBean(Class<T> clazz) {
        if (specificBeans == null) {
            specificBeans = beanProcessor.processSpecificBeans(clazz);
        }
        return specificBeans.get(specificFlowDecider.currentSpecific());
    }

    public abstract T primaryBean();
}
