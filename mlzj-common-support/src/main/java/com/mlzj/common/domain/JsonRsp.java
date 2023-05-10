package com.mlzj.common.domain;

import lombok.Data;

/**
 * @author yhl
 * @date 2023/4/21
 * json返回数据
 */
@Data
public class JsonRsp <T>{

    /**
     * code
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public JsonRsp() {

    }

    public JsonRsp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
