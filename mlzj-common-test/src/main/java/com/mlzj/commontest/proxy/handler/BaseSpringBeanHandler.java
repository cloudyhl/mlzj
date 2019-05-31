package com.mlzj.commontest.proxy.handler;

import com.mlzj.common.utils.CharacterUtils;
import com.mlzj.commontest.proxy.factory.ProxyFactoryBean;
import com.mlzj.commontest.proxy.interfaces.Animal;
import com.mlzj.commontest.proxy.interfaces.Dynamic;
import com.mlzj.commontest.proxy.interfaces.DynamicInterface;
import com.mlzj.commontest.proxy.interfaces.impl.SimpleDynamicImpl;
import com.mlzj.commontest.proxy.model.Cat;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2019/5/30
 */
@Component
public class BaseSpringBeanHandler implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Dynamic.class);
        GenericBeanDefinition definition = (GenericBeanDefinition) beanDefinitionBuilder.getRawBeanDefinition();
        definition.getPropertyValues().add("clazz",definition.getBeanClassName());
        definition.setBeanClass(ProxyFactoryBean.class);
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        registry.registerBeanDefinition(CharacterUtils.toLowerCaseFirstOne(Dynamic.class.getSimpleName()),definition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
