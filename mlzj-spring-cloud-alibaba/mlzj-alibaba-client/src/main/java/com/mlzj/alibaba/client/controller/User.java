package com.mlzj.alibaba.client.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
@ApiModel
public class User {

    @ApiModelProperty
    private User user;
    @ApiModelProperty
    private List<User> userList;
}
