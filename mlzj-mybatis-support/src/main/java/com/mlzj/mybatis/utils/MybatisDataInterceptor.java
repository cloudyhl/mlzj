package com.mlzj.mybatis.utils;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.mlzj.mybatis.model.authority.UserAuthority;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * @author yhl
 * @date 2019/3/4
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class MybatisDataInterceptor extends AbstractSqlParserHandler implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object paramObj = boundSql.getParameterObject();
        UserAuthority userAuthority = null;
        if (paramObj instanceof UserAuthority) {
            userAuthority = (UserAuthority) paramObj;
        } else if (paramObj instanceof Map) {
            for (Object arg : ((Map) paramObj).values()) {
                if (arg instanceof UserAuthority) {
                    userAuthority = (UserAuthority) arg;
                    break;
                }
            }
        }
        String originalSql = boundSql.getSql();
        //用where拆分sql默认
        String[] originalSplitSql = StringUtils.split(originalSql, "where");
        //截取第一个where之前的sql
        String firstSqlString = originalSplitSql[0];
        StringBuilder firstSqlStringBuilder = new StringBuilder(firstSqlString);
        if (userAuthority != null) {
            Map<String, Object> authorityMap = userAuthority.getAuthority();
            //为第一条sql拼接where
            firstSqlStringBuilder.append("where");
            //匹配用户所有权限根据类型拼装sql
            for (Map.Entry<String, Object> authorityEntry : authorityMap.entrySet()) {
                if (StringUtils.isNotBlank(authorityEntry.getKey()) && authorityEntry.getValue() instanceof String) {
                    firstSqlStringBuilder.append(" and ").append(authorityEntry.getKey()).append(" = ").append("'").append(authorityEntry.getValue()).append("'");
                }
                if (StringUtils.isNotBlank(authorityEntry.getKey()) && authorityEntry.getValue() instanceof Integer) {
                    firstSqlStringBuilder.append(" and ").append(authorityEntry.getKey()).append(" = ").append(authorityEntry.getValue());
                }
            }
            for (int count = 0; count < originalSplitSql.length; count++) {
                if (count != 0){
                    firstSqlStringBuilder.append(originalSplitSql[count]);
                }
            }
            metaObject.setValue("delegate.boundSql.sql", firstSqlStringBuilder.toString());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}