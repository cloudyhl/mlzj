package com.mlzj.dubbo.provider;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.mlzj.dubbo.interfaces.service.user.UserService;
import com.mlzj.dubbo.interfaces.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjDubboProvicerApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private ApplicationContext applicationContext;
    @Test
    public void contextLoads() {
        RegistryConfig bean = applicationContext.getBean(RegistryConfig.class);
        ProtocolConfig bean1 = applicationContext.getBean(ProtocolConfig.class);
        System.out.println(111);
    }

}
