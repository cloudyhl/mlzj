package com.mlzj.commontest.proxy.handler;

import com.mlzj.commontest.proxy.interfaces.impl.SimpleDynamicImpl;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.CtNewMethod;
import org.apache.ibatis.javassist.bytecode.AnnotationsAttribute;
import org.apache.ibatis.javassist.bytecode.ConstPool;
import org.apache.ibatis.javassist.bytecode.annotation.Annotation;
import org.apache.ibatis.javassist.bytecode.annotation.ClassMemberValue;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author yhl
 * @date 2019/5/30
 */
public class DynamicProxy implements InvocationHandler {

    private Class baseClazz = SimpleDynamicImpl.class;

    private Object targetObject;

    private DynamicHandler dynamicHandler = new DynamicHandler();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        if (Object.class.equals(method.getDeclaringClass())) {
//            return method.invoke(targetObject, args);
//        } else {
//            //如果没有实现,调用
//            Method declaredMethod = targetObject.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
//            return declaredMethod.invoke(targetObject, args);
//        }
        return method.invoke(targetObject,args);
    }

    public Object getProxy(Class clazz) throws Exception {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        ClassPool pool = new ClassPool(true);
        CtClass cc = pool.get(baseClazz.getName());
        ConstPool constPool = cc.getClassFile().getConstPool();
        for (Method method : declaredMethods) {
            CtClass[] paramCtClass = new CtClass[method.getParameterTypes().length];
            for (int i = 0;i < method.getParameterTypes().length; i++) {
                CtClass ctClass = pool.get(method.getParameterTypes()[i].getName());
                paramCtClass[i] = ctClass;
            }
            CtClass[] exceptionCtClass = new CtClass[method.getExceptionTypes().length];
            for (int j = 0; j < method.getExceptionTypes().length; j++){
                CtClass ctClass = pool.get(method.getExceptionTypes()[j].getName());
                exceptionCtClass[j] = ctClass;
            }
            String methodBody ="return \""  +method.getName()+"\";";
            CtMethod make = CtNewMethod.make(Modifier.PUBLIC, pool.get(method.getReturnType().getName()), method.getName(), paramCtClass, exceptionCtClass, methodBody, cc);
            //AnnotationsAttribute.visibleTag 运行时可见，invisibleTag 为运行时不可见
            AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation(Transactional.class.getName(),constPool);
            annotation.addMemberValue("rollbackFor",new ClassMemberValue(Exception.class.getName(),constPool));
            annotationsAttribute.addAnnotation(annotation);
            make.getMethodInfo().addAttribute(annotationsAttribute);
            cc.addMethod(make);
        }
        cc.addInterface(pool.get(clazz.getName()));
        Class<?> classByBytes = dynamicHandler.findClassByBytes(baseClazz.getName(), cc.toBytecode());
        Method[] declaredMethods1 = classByBytes.getDeclaredMethods();
        Arrays.stream(declaredMethods1).forEach( method ->{
            java.lang.annotation.Annotation[] annotations = method.getAnnotations();
            Arrays.stream(annotations).forEach(annotation -> {
                System.out.println("--------------");
                System.out.println(annotation);});
        });
        this.targetObject = classByBytes.newInstance();
        //return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return Proxy.newProxyInstance(classByBytes.getClassLoader(),classByBytes.getInterfaces(),this);
    }
}
