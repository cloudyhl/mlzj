package com.mlzj.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mlzj.dubbo.interfaces.service.user.UserService;
import com.mlzj.dubbo.interfaces.vo.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhl
 * @date 2019/4/16
 */
@Component
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> selectUser() {
        List<User> userList = new ArrayList<>(4);
        for (int i = 0; i< 3000;i++){
            userList.add(new User("张三"+i,12+i,"四川"+i));
            if (i%10000 == 0){
                System.out.println(i);
            }
        }

        userList.add(new User("张四",13,"四川"));
        userList.add(new User("张五",14,"四川"));
        userList.add(new User("张六",15,"四川"));
        return userList;
    }
}
