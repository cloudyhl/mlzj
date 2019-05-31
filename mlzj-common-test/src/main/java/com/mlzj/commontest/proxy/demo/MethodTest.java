package com.mlzj.commontest.proxy.demo;

import com.mlzj.commontest.proxy.handler.AppClassLoader;
import com.mlzj.commontest.proxy.model.UserInfo;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.CtNewMethod;

/**
 * @author yhl
 * @date 2019/5/29
 */
public class MethodTest {
    public static void main(String[] args) {
        try {
//            String className = UserInfo.class.getName();
//            UserInfo userInfo = new UserInfo();
//            userInfo.setName("test");
//            userInfo.setId(1);
//            System.out.println("before:" + userInfo);
//            ClassPool pool = ClassPool.getDefault();
//            CtClass cc = pool.get(className);
//            CtMethod mthd = CtNewMethod.make("public String test() { return \"test() is called \"+ toString();  }", cc);
//            cc.addMethod(mthd);
//
//            AppClassLoader appClassLoader = AppClassLoader.getInstance();
//            Class<?> clazz = appClassLoader.findClassByBytes(className, cc.toBytecode());
////            clazz.getDeclaredConstructor().newInstance();
//            Object obj = appClassLoader.getObj(clazz,userInfo);
//            System.out.println("after:" + obj);
//            //测试反射调用添加的方法
//            System.out.println(obj.getClass().getDeclaredMethod("test").invoke(obj));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
