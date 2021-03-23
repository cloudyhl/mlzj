package com.mlzj.commontest.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author yhl
 * @date 2019/4/19
 */
@Data
@TableName("mlzj_user")
@AllArgsConstructor
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String userName;

    private Integer age;

    private String address;

    public User() {
    }
}
