package com.mlzj.mybatis.utils;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yhl
 * @date 2022/8/1
 */
public class CommonUtils {

    private CommonUtils() {
    }

    /**
     * 获取类声明方法集合
     *
     * @param clazz 类型
     */
    public static Map<String, Method> getDeclaredMethodsMap(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            methodMap.put(declaredMethod.getName(), declaredMethod);
        }
        return methodMap;
    }


    /**
     * 清除所有的html标签
     * @param text 文本
     * @return 去除html标签后的文本
     */
    public static String removeAllHtmlTag(String text) {
        if (StringUtils.isNotBlank(text)) {
            text= text.replaceAll("<[.[^<]]*>", "");
        }
        return text;
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 首字母大写后的字符串
     */
    public static String toUpperCaseFist(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }

    }

    /**
     * 通过字段名获取 get方法名称
     * @param fieldName 字段名称
     */
    public static String getGetMethodName(String fieldName) {
        return "get"+toUpperCaseFist(fieldName);
    }

    /**
     * 判断是否为空
     * @param obj 对象
     * @return 是否为空
     */
    public static boolean notEmpty(Object obj) {
        Boolean b = null;
        if (obj instanceof String) {
            b = StringUtils.isNotBlank((String)obj);
        } else {
            b = obj != null;
        }
        return b;
    }

}
