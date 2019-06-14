package com.mlzj.kafka;

import com.mlzj.kafka.demo.producer.SimpleMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjKafkaSupportApplicationTests {

    @Resource
    private SimpleMessageSender simpleMessageSender;

    @Test
    public void contextLoads() {
        for (int i = 0; i< 20; i++){
            int finalI = i;
            new Thread(()->{
                for (int j = 0; j<1000; j++){
                    simpleMessageSender.sendMessage("message"+j* finalI);
                }
            }).start();
        }


    }

    @Test
    public void sendmessage() {
        for (int i = 0; i< 20; i++){
            simpleMessageSender.sendMessage("message"+i);

        }


    }

}
