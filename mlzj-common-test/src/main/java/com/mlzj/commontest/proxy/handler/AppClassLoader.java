package com.mlzj.commontest.proxy.handler;

import java.lang.reflect.Field;

/**
 * @author yhl
 * @date 2019/5/29
 */
public class AppClassLoader extends ClassLoader {

    private static class SingletonHolder {
        public final static AppClassLoader instance = new AppClassLoader();
    }

    public static AppClassLoader getInstance() {
        return SingletonHolder.instance;
    }


    private AppClassLoader() {

    }

    /**
     * 通过classBytes加载类
     *
     * @param className
     * @param classBytes
     * @return
     */
    public Class<?> findClassByBytes(String className, byte[] classBytes) {
        return defineClass(className, classBytes, 0, classBytes.length);
    }

//    /**
//     * 复制对象所有属性值,并返回一个新对象
//     *
//     * @param srcObj
//     * @return
//     */
//    public Object getObj(Class<?> clazz, Object srcObj) {
//        try {
//            Object newInstance = clazz.getDeclaredConstructor().newInstance();
//            Field[] fields = srcObj.getClass().getDeclaredFields();
//            for (Field oldInstanceField : fields) {
//                String fieldName = oldInstanceField.getName();
//                oldInstanceField.setAccessible(true);
//                Field newInstanceField = newInstance.getClass().getDeclaredField(fieldName);
//                newInstanceField.setAccessible(true);
//                newInstanceField.set(newInstance, oldInstanceField.get(srcObj));
//            }
//            return newInstance;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
