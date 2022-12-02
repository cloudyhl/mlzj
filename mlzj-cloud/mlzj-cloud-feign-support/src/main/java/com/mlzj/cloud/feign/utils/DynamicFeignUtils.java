package com.mlzj.cloud.feign.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author yhl
 * @date 2022/12/1
 */
public class DynamicFeignUtils {

    private DynamicFeignUtils() {
    }

    /**
     * 获取get请求入参
     * @param data 数据
     * @return get请求使用的参数
     */
    public static String buildGetParamStr(Object data) {
        Map<String, String> map = JSON.parseObject(JSON.toJSONString(data), new TypeReference<Map<String, String>>(){});
        List<String> paramList = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            paramList.add(entry.getKey() + "=" + entry.getValue());
        }
        return String.join("&", paramList);
    }

}
