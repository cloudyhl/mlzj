package com.mlzj.commontest.proxy.handler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @author yhl
 * @date 2019/5/30
 */
@Slf4j
public class DynamicHandler extends ClassLoader{

    public Class<?> findClassByBytes(String className, byte[] classBytes) {
        return defineClass(className, classBytes, 0, classBytes.length);
    }

    /**
     * 复制对象所有属性值,并返回一个新对象
     *
     * @param srcObj 原对象
     * @return 新对象
     */
    public Object getObj(Class<?> clazz, Object srcObj) {
        try {
            Object newInstance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new DynamicProxy());
            //Object newInstance = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = srcObj.getClass().getDeclaredFields();
            for (Field oldInstanceField : fields) {
                String fieldName = oldInstanceField.getName();
                oldInstanceField.setAccessible(true);
                Field newInstanceField = newInstance.getClass().getDeclaredField(fieldName);
                newInstanceField.setAccessible(true);
                newInstanceField.set(newInstance, oldInstanceField.get(srcObj));
            }
            return newInstance;
        } catch (Exception e) {
            log.error("Get a new instance has a exception",e);
        }
        return null;
    }
}
