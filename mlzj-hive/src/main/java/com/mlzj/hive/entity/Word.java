package com.mlzj.hive.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yhl
 * @date 2022/8/15
 */
@Data
@TableName("word")
public class Word {

    private String words;
}
