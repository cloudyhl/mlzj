package com.mlzj.common.domain;

/**
 * @author yhl
 * @date 2023/4/21
 */
public class JsonRspHelper {

    private static final String SUCCESS_CODE = "0";
    private static final String SUCCESS_MSG = "操作成功";
    private static final String FAIL_CODE = "99999";
    private static final String FAIL_MSG = "系统错误";

    public static <T> JsonRsp<T> success(){
        return getSuccessObj();
    }

    public static <T> JsonRsp<T> success(T data){
        JsonRsp<T> successObj = getSuccessObj();
        successObj.setData(data);
        return successObj;
    }

    private static <T> JsonRsp<T> getSuccessObj() {
        return new JsonRsp<>(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static <T> JsonRsp<T> fail(){
        return getFailObj();
    }

    public static <T> JsonRsp<T> fail(T data){
        JsonRsp<T> failObj = getFailObj();
        failObj.setData(data);
        return failObj;
    }

    private static <T> JsonRsp<T> getFailObj() {
        return new JsonRsp<>(FAIL_CODE, FAIL_MSG);
    }
}
