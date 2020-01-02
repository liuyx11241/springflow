package com.github.liuyx11241.springflow.config;

import com.github.liuyx11241.springflow.stereotype.EnableSpecificFlow;
import com.github.liuyx11241.springflow.stereotype.SpecificFlow;
import com.github.liuyx11241.springflow.stereotype.SpecificFlowQualifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@ConditionalOnBean(annotation = {EnableSpecificFlow.class})
@ConditionalOnWebApplication
public class SpecificFlowBeanProcessor implements BeanFactoryPostProcessor {
    private ConfigurableListableBeanFactory factory;

    private static final String ANNOTATION_NAME = SpecificFlowQualifier.class.getName();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        this.factory = factory;
    }

    public <T> Map<Class<? extends SpecificFlow>, T> processSpecificBeans(Class<T> clazz) {
        final Map<String, T> beansOfType = factory.getBeansOfType(clazz);

        final Map<Class<? extends SpecificFlow>, T> result = new HashMap<>(beansOfType.size());

        for (Map.Entry<String, T> entry : beansOfType.entrySet()) {
            final BeanDefinition beanDefinition = factory.getBeanDefinition(entry.getKey());
            final Map<String, Object> qualifierAttributes = new HashMap<>();

            if (beanDefinition.getSource() instanceof AnnotatedTypeMetadata) {
                if (((AnnotatedTypeMetadata) beanDefinition.getSource()).isAnnotated(ANNOTATION_NAME)) {
                    qualifierAttributes.putAll(
                            ((AnnotatedTypeMetadata) beanDefinition.getSource()).getAnnotationAttributes(ANNOTATION_NAME));
                }
            } else if (beanDefinition instanceof AnnotatedBeanDefinition) {
                final AnnotationMetadata metadata = ((AnnotatedBeanDefinition) beanDefinition).getMetadata();
                if (metadata.hasAnnotation(ANNOTATION_NAME) || metadata.hasMetaAnnotation(ANNOTATION_NAME)) {
                    qualifierAttributes.putAll(metadata.getAnnotationAttributes(ANNOTATION_NAME));
                }
            }

            if (qualifierAttributes.containsKey("value")) {
                final Class<? extends SpecificFlow> value = (Class<? extends SpecificFlow>) qualifierAttributes.get("value");
                result.put(value, entry.getValue());
            }
        }
        return result;
    }
}
