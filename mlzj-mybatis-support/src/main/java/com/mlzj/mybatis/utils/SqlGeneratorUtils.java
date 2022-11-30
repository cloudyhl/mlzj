package com.mlzj.mybatis.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mlzj.mybatis.constants.SqlTemplateConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author yhl
 * @date 2022/11/21
 */
@SuppressWarnings("all")
public class SqlGeneratorUtils {

    private SqlGeneratorUtils() {}

    /**
     * 获取通用的插入语句
     *
     * @param tClass  目标类
     * @param dataObj 数据对象
     * @return 数据结果
     * @throws Exception 异常信息
     */
    public static String getInsertSql(Class<?> tClass, Object dataObj) throws Exception {
        Object[] objects = new Object[3];
        Field[] declaredFields = tClass.getDeclaredFields();
        Map<String, Method> declaredMethodsMap = CommonUtils.getDeclaredMethodsMap(tClass);
        List<String> fieldList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        //获取所有的列
        for (Field declaredField : declaredFields) {
            Method method = declaredMethodsMap.get(CommonUtils.getGetMethodName(declaredField.getName()));
            TableId tableId = declaredField.getAnnotation(TableId.class);
            //如果存在tableId字段 则自动生成id字段的列和对应的插入值
            if (tableId != null && CommonUtils.notEmpty(method.invoke(dataObj))) {
                fieldList.add(tableId.value());
                valueList.add(setCommonValue(declaredField, method.invoke(dataObj)));
            }
            //通过tableField获取所有字段的属性 设置字段名和属性
            TableField tableField = declaredField.getAnnotation(TableField.class);
            if (tableField != null && CommonUtils.notEmpty(method.invoke(dataObj))) {
                fieldList.add(tableField.value());
                valueList.add(setCommonValue(declaredField, method.invoke(dataObj)));
            }
        }
        objects[0] = tClass.getAnnotation(TableName.class).value();
        objects[1] = String.join(",", fieldList);
        objects[2] = String.join(",", valueList);
        return String.format(SqlTemplateConstants.COMMON_INSERT_TEMPLATE, objects);
    }


    /**
     * 设置通用值
     * @param declaredField 申明字段
     * @param dataObj 数据
     * @return 拼接的字符串
     */
    private static String setCommonValue(Field declaredField, Object dataObj) {
        if (!CommonUtils.notEmpty(dataObj)) {
            return "NULL";
        }
        if (declaredField.getType().equals(Date.class)) {
            return "'" + DateFormatUtils.format((Date) dataObj,SqlTemplateConstants.TIME_FORMAT_PATTERN ) + "'";
        }
        if (declaredField.getType().equals(String.class)) {
            return "'" + dataObj + "'";
        }
        return String.valueOf(dataObj);
    }


    /**
     * 获取通用批量的插入语句
     *
     * @param tClass 目标类
     * @return 数据结果
     * @throws Exception 异常信息
     */
    public static String getBatchInsertSql(Class<?> tClass,List<?> dataList) throws Exception {
        Object[] objects = new Object[3];
        Field[] declaredFields = tClass.getDeclaredFields();
        Map<String, Method> declaredMethodsMap = CommonUtils.getDeclaredMethodsMap(tClass);
        List<String> fieldList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            TableId tableId = declaredField.getAnnotation(TableId.class);
            if (tableId != null) {
                fieldList.add(tableId.value());
            }
            TableField tableField = declaredField.getAnnotation(TableField.class);
            if (tableField != null) {
                fieldList.add(tableField.value());
            }
        }
        for (Object dataObj : dataList) {
            List<String> itemValueList = new ArrayList<>();
            for (Field declaredField : declaredFields) {
                TableId tableId = declaredField.getAnnotation(TableId.class);
                Method method = declaredMethodsMap.get(CommonUtils.getGetMethodName(declaredField.getName()));
                if (tableId != null) {
                    itemValueList.add(setCommonValue(declaredField, method.invoke(dataObj)));
                }
                TableField tableField = declaredField.getAnnotation(TableField.class);
                if (tableField != null) {
                    itemValueList.add(setCommonValue(declaredField, method.invoke(dataObj)));
                }
            }
            valueList.add("(" + String.join(",", itemValueList) + ")");
        }

        objects[0] = tClass.getAnnotation(TableName.class).value();
        objects[1] = String.join(",", fieldList);
        objects[2] = String.join(",", valueList);
        return String.format(SqlTemplateConstants.COMMON_BATCH_INSERT_TEMPLATE, objects);
    }
}
