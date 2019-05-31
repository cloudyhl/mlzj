package com.mlzj.commontest.proxy.demo;

import com.mlzj.commontest.proxy.handler.CatProxyHandler;
import com.mlzj.commontest.proxy.interfaces.Animal;
import com.mlzj.commontest.proxy.model.Cat;

/**
 * @author yhl
 * @date 2019/5/29
 */
public class ProxyTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        CatProxyHandler catProxyHandler = new CatProxyHandler();
        //Animal proxy = (Animal) catProxyHandler.getProxy(new Cat());
        //Animal targetProxy = (Animal) catProxyHandler.getTargetProxy(Cat.class);
        //Animal targetCglib = (Animal)catProxyHandler.getTargetCglib(new Cat());
        Cat targetCglib = (Cat)catProxyHandler.getTargetCglib(new Cat("1","1"));
        Object proxy = catProxyHandler.getInterface(Animal.class);
        System.out.println(targetCglib.getAge());
        //proxy.say();
        //targetProxy.say();
        targetCglib.say();
        System.out.println(proxy);

    }
}
