package com.mlzj.activity.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yhl
 * @date 2022/7/29
 */
@Data
@ApiModel
public class UserAddDto {

    @ApiModelProperty(value = "用户id", example = "100001")
    private String userId;

    @ApiModelProperty(value = "用户名", example = "张三")
    private String userName;

}
