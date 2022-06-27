package com.nice.cloud.init;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.nice.cloud.AutoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class CustomBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(AutoConfiguration.class);
        beanDefinitionRegistry.registerBeanDefinition(UuidUtils.generateUuid(), bd);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
