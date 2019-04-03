package com.mlzj.common.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yhl
 * @date 2019/3/26
 */
@Getter
@Setter
public class CommonBusinessException extends RuntimeException {
    private static final long serialVersionUID = -7153970261734986895L;
    /**
     * 错误状态码
     */
    private Integer code;
    /**
     * 错误描述
     */
    private String description;

    public CommonBusinessException(String message) {
        super(message);
        this.code = 500;
        this.description = message;
    }
    public CommonBusinessException(BusinessExceptionType exceptionType) {
        super(exceptionType.getDescribe());
        this.code = exceptionType.getCode();
        this.description = exceptionType.getDescribe();
    }

}
