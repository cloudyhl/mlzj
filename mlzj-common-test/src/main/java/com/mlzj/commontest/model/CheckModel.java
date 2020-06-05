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
public class CheckModel {

    @ApiModelProperty(value = "name", example = "this is name")
    private String modelName;

    @ApiModelProperty(value = "", dataType = "ChildCheckModel")
    private ChildCheckModel childCheckModel;
}
