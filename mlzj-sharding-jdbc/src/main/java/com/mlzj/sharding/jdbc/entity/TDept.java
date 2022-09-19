package com.mlzj.sharding.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import lombok.Data;

/**
 * @author yhl
 * @date 2022/9/16
 */
@Data
@TableName("t_dept")
public class TDept implements Serializable {


    @TableId
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String deptName;

    private String posi;

}
