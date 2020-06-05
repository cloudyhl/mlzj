package com.mlzj.common.validate;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yhl
 * @date 2020/6/3
 */
@Component
public class ValidationFactory {

    @Resource
    private Map<String, Validation> validationMap;

    public Validation getInstance(String validationName){
        return validationMap.get(validationName);
    }
}
