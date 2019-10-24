package com.mlzj.common.domain;


/**
 * 普通的业务异常
 * @author yhl
 * @date 2019/8/29
 */
public class BusinessCommonException extends RuntimeException implements BusinessExceptionType {

    private static final long serialVersionUID = 7268856116432456434L;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 描述
     */
    private final String description;

    public BusinessCommonException(ExceptionType exceptionType){
        super(exceptionType.getDescription());
        this.code = exceptionType.getCode();
        this.description = exceptionType.getDescription();
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescribe() {
        return this.description;
    }
}
