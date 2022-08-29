package com.mlzj.hbase.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yhl
 * @date 2022/8/18
 */
@Data
@TableName("\"hbase_test\".\"user\"")
public class UserEntity {

    private String id;

    private String userName;

    private String birthday;

}
