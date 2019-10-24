package com.mlzj.common.utils;

import com.mlzj.common.domain.BusinessCommonException;
import com.mlzj.common.domain.ExceptionType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * 断言工具类
 * @author yhl
 * @date 2019/8/29
 */
public class AssertUtils {

    public static void notNull(Object obj, ExceptionType exceptionType){
        if (obj == null){
            throw new BusinessCommonException(exceptionType);
        }
    }

    public static void notBlank(String str, ExceptionType exceptionType){
        if (str == null || StringUtils.isBlank(str)){
            throw new BusinessCommonException(exceptionType);
        }
    }

    public static void isNull(Object obj, ExceptionType exceptionType){
        if (obj != null){
            throw new BusinessCommonException(exceptionType);
        }
    }

    public static void isEmpty(Collection<Object> objs, ExceptionType exceptionType){
        if (CollectionUtils.isNotEmpty(objs)){
            throw new BusinessCommonException(exceptionType);
        }
    }
    private AssertUtils(){}

}
