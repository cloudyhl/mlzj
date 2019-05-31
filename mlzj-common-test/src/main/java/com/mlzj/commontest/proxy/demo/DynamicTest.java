package com.mlzj.commontest.proxy.demo;

import com.mlzj.commontest.proxy.handler.DynamicProxy;
import com.mlzj.commontest.proxy.interfaces.Dynamic;

import java.lang.reflect.Method;

/**
 * @author yhl
 * @date 2019/5/30
 */
public class DynamicTest {
    public static void main(String[] args) throws Exception {
        DynamicProxy dynamicProxy = new DynamicProxy();
        Object proxy = dynamicProxy.getProxy(Dynamic.class);
    }
}
