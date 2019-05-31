package com.mlzj.commontest.proxy.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author yhl
 * @date 2019/5/29
 */
@Slf4j
public class CatProxyHandler implements InvocationHandler {

    private Object target;
    private Class clazz;

    public Object getProxy(Object target){
        //this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);

    }
    public Object getTargetProxy(Class clazz){
        this.clazz = clazz;
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public Object getTargetCglib(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    public Object getTargetCglib(Class clazz) throws IllegalAccessException, InstantiationException {
        this.clazz = clazz;
        this.target = clazz.newInstance();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public Object getInterface(Class clazz) throws IllegalAccessException, InstantiationException {
        this.clazz = clazz;
        return Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        log.info("info----->{},{},{}",o,method,objects);
        method.invoke(target,objects);
        return null;
    }
}
