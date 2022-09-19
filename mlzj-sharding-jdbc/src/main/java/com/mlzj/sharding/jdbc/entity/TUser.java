package com.mlzj.sharding.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import lombok.Data;

@Data
@TableName("t_user")
public class TUser {

    @TableId
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private Date createTime;
}
