package com.mlzj.mlzjsecurity;

import com.mlzj.mlzjsecurity.dao.UserRepository;
import com.mlzj.mlzjsecurity.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjSecurityApplicationTests {

    @Resource
    private UserRepository userRepository;
    @Test
    public void contextLoads() {
    }
    @Test
    public void saveTest(){
        SysUser sysUser = userRepository.findByUsername("mark");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(sysUser);
    }

}
