package com.mlzj.commontest.proxy.factory;

import com.mlzj.commontest.proxy.handler.DynamicProxy;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author yhl
 * @date 2019/5/29
 */
@Data
public class ProxyFactoryBean implements FactoryBean {

    private Class clazz;

    @Override
    public Object getObject() throws Exception {
        return new DynamicProxy().getProxy(clazz);
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
