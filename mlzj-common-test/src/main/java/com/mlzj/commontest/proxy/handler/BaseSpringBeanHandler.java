package com.mlzj.commontest.proxy.handler;

import com.mlzj.common.utils.CharacterUtils;
import com.mlzj.commontest.proxy.factory.ProxyFactoryBean;
import com.mlzj.commontest.proxy.interfaces.Dynamic;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
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
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ProxyFactoryBean.class);
        GenericBeanDefinition definition = (GenericBeanDefinition) beanDefinitionBuilder.getRawBeanDefinition();
        //设置beanDefinition中的属性值,例如clazz  设置为Dynamic.class
        definition.getPropertyValues().add("clazz",Dynamic.class);
        //definition.setBeanClass(ProxyFactoryBean.class);
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        definition.setScope(BeanDefinition.SCOPE_SINGLETON);
        registry.registerBeanDefinition(CharacterUtils.toLowerCaseFirstOne(Dynamic.class.getSimpleName()),definition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
