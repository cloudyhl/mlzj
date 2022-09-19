package com.mlzj.international.aspect;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Sets;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author yhl
 * @date 2022/9/12
 */
@Slf4j
@Aspect
@Component
@SuppressWarnings("all")
public class I18nTmeAspect {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final String SET_METHOD = "set";
    private final String GET_METHOD = "get";
    private final String PACKAGE_START = "com.mlzj";
    private final HashSet ignoreSet = Sets.newHashSet("Integer", "Long", "Double", "Short", "String", "Boolean", "Float", "Character", "Byte");

    /**
     * within 使用类上的注解匹配
     */
    @Around(value = "@annotation(com.mlzj.international.annotation.I18nTime)|| @within(com.mlzj.international.annotation.I18nTime)")
    public Object i18nTIme(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String timeZone = request.getHeader("x-client-time-zone");
        log.info("[I18nDateAspect][dealDateParamByTimeZone] timeZone param in header is: {}.", timeZone);
        Object resultData = proceedingJoinPoint.proceed();
        //判断没有传时区和东八区时间不需要转换
        if (StringUtils.isBlank(timeZone) || 8 * 60 * 60 * 1000 == TimeZone.getTimeZone(timeZone).getRawOffset()) {
            return resultData;
        }
        if (resultData == null) {
            return null;
        }
        this.timeCheck(resultData, timeZone);
        return resultData;
    }

    /**
     * 返回时间校验并转换时区
     * @param objData 请求参数
     * @param timeZone 时区
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void timeCheck(Object objData, String timeZone) throws InvocationTargetException, IllegalAccessException, ParseException {
        if (objData!= null && objData instanceof Collection) {
            for (Object objDatum : (Collection) objData) {
                this.baseCheck(objDatum, timeZone);
            }
            return;
        }
        if (objData!= null && objData instanceof Map) {
            for (Entry<Object, Object> entry : ((Map<Object, Object>) objData).entrySet()) {
                Object o = this.baseCheck(entry.getValue(), timeZone);
                if (o != null) {
                    entry.setValue(o);
                }
            }
            return;
        }
        if (objData!= null && objData instanceof IPage) {
            this.baseCheck(objData, timeZone);
            return;
        }
        if (objData!= null && objData.getClass().getName().startsWith(PACKAGE_START)) {
            this.baseCheck(objData, timeZone);
            return;
        }

    }

    /**
     * 基础类时区转换方法
     * @param objData 请求参数
     * @param timeZone 时区
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object baseCheck(Object objData, String timeZone) throws InvocationTargetException, IllegalAccessException, ParseException {
        if (objData == null) {
            return null;
        }
        if (objData!= null && objData instanceof Collection) {
            for (Object objDatum : (Collection) objData) {
                this.baseCheck(objDatum, timeZone);
            }
            return null;
        }
        if (objData!= null && objData instanceof Map) {
            for (Entry<Object, Object> entry : ((Map<Object, Object>) objData).entrySet()) {
                Object o = this.baseCheck(entry.getValue(), timeZone);
                if (o != null) {
                    entry.setValue(o);
                }
            }
            return null;
        }
        Class<?> aClass = objData.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Map<String, Method> methodMap = this.getMethodMap(aClass);
        if (objData != null && objData instanceof Date) {
            return this.dateToTimeDate((Date)objData, timeZone);
        }
        if (objData != null && objData instanceof String && isDateString((String) objData)) {
            return this.stringToTimeStr((String) objData, timeZone);
        }
        for (Field field : fields) {
            String getMethodName = GET_METHOD + this.toUpperCaseFist(field.getName());
            Method getMethod = methodMap.get(getMethodName);
            String setMethodName = SET_METHOD + this.toUpperCaseFist(field.getName());
            Method method = methodMap.get(setMethodName);
            if (getMethod == null || getMethod.invoke(objData) == null) {
                continue;
            }
            if (field.getType().equals(Date.class)) {
                Date time = (Date) getMethod.invoke(objData);
                method.invoke(objData, this.dateToTimeDate(time, timeZone));
                continue;
            }
            if (field.getType().equals(String.class) && getMethod != null && isDateString((String) getMethod.invoke(objData))) {
                String targetTimeString = this.stringToTimeStr((String) getMethod.invoke(objData), timeZone);
                method.invoke(objData, targetTimeString);
                continue;
            }
            if (getMethod != null && !ignoreSet.contains(getMethod.invoke(objData).getClass().getSimpleName())) {
                this.timeCheck(getMethod.invoke(objData), timeZone);
            }
        }
        return null;
    }

    /**
     * 将date类型的时间转换为新的时区
     * @param time 原时区的时间
     * @param targetTimeZone 目标时区的时间
     * @return 目标时区的时间
     */
    private Date dateToTimeDate(Date time, String targetTimeZone) throws ParseException {
        String dateStr = DateFormatUtils.format(time.getTime(), DATE_FORMAT_PATTERN, TimeZone.getTimeZone(targetTimeZone));
        return DateUtils.parseDate(dateStr, DATE_FORMAT_PATTERN);
    }

    private String stringToTimeStr(String str, String targetTimeZone) throws ParseException {
        Date date = DateUtils.parseDate(str, DATE_FORMAT_PATTERN);
        return DateFormatUtils.format(date.getTime(), DATE_FORMAT_PATTERN, TimeZone.getTimeZone(targetTimeZone));
    }

    private String toUpperCaseFist(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return String.valueOf(Character.toUpperCase(str.charAt(0))) + str.substring(1);
        }
    }

    private Map<String, Method> getMethodMap(Class<?> aClass) {
        Map<String, Method> resultMap = new HashMap<>(32);
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            resultMap.put(declaredMethod.getName(), declaredMethod);
        }
        return resultMap;
    }

    /**
     * 判断字符串是否为时间
     *
     * @param str 传入的字符串
     * @return 是否为时间类型
     */
    private static boolean isDateString(String str) {
        String pattern = "^[0-9]{4}-([0]{1}[1-9]{1}|[1]{1}[0-2]{1})-([0]{1}[1-9]{1}|[1-2]{1}[0-9]{1}|[3]{1}[0-1]{1}) ([0-1]{1}[0-9]{1}|[2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}";
        return Pattern.matches(pattern, str);
    }

}
