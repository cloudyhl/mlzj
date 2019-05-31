package com.mlzj.mongodb.demo.utils;

import com.mlzj.mongodb.demo.model.OnlineUser;
import org.springframework.stereotype.Component;

/**
 * 用户工具类
 * @author yhl
 * @date 2019/5/24
 */
@Component
public class UserUtils {

    /**
     * 模拟返回线上用户,具体应用可写自己实现
     * @return 线上用户
     */
    public OnlineUser getOnlineUser(){
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setAccount("cloud");
        onlineUser.setLevel("2");
        onlineUser.setUserName("张三");
        return onlineUser;
    }

}
