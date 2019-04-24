package com.mlzj.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlzj.dubbo.interfaces.service.user.UserService;
import com.mlzj.dubbo.interfaces.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjDubboConsumerApplicationTests {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @Test
    public void contextLoads() {
        long l  = System.currentTimeMillis();
        List<User> users = userService.selectUser();
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(users);
        System.out.println("go out");
        System.out.println(System.currentTimeMillis()-l);
    }

}
