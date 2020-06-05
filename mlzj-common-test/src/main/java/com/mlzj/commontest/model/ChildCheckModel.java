package com.mlzj.commontest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yhl
 * @date 2020/6/3
 */
@ApiModel
@Data
public class ChildCheckModel {

    @ApiModelProperty(value = "子模块名称", example = "this is child model name")
    private String childModelName;
}
