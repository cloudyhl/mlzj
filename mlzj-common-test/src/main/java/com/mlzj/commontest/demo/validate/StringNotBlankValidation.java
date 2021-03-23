package com.mlzj.commontest.demo.validate;

import com.mlzj.common.validate.Validation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 字符串校验是否为空
 *
 * @author yhl
 * @date 2020/6/3
 */
@Component
public class StringNotBlankValidation implements Validation {
    @Override
    public boolean validate(Object obj) {
        return StringUtils.isNotBlank((String) obj);
    }
}
