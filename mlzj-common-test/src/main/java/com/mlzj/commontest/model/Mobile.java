package com.mlzj.commontest.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author yhl
 * @date 2019/7/21
 */
@Data
public class Mobile {

    @Min(value = 0 ,message = "最小值不能小于{value}")
    @Max(value = 50, message = "最大值不能大于{value}")
    private Integer longSize;

    private Integer high;

}
