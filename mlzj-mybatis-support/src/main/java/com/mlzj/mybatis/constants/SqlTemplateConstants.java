package com.mlzj.mybatis.constants;

/**
 * @author yhl
 * @date 2022/11/21
 */
public class SqlTemplateConstants {

    private SqlTemplateConstants() {
    }

    /**
     * 通用插入sql模板
     */
    public static final String COMMON_INSERT_TEMPLATE = "INSERT INTO %s(%s) VALUES(%s)";

    /**
     * 批量插入通用
     */
    public static final String COMMON_BATCH_INSERT_TEMPLATE = "INSERT INTO %s(%s) VALUES %s";

    /**
     * 时间类型格式化
     */
    public static final String TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";



}
