package com.mlzj.commontest.demo.net.frame.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 注册时间
 * @author yhl
 * @date 2020/5/6
 */
@AllArgsConstructor
@Data
public class RegisterEvent {
    private String ip;

    private String name;
}
