package com.mlzj.rocketmq;

import com.mlzj.rocketmq.demo.MyConcurrentProducer;
import com.mlzj.rocketmq.demo.MyOrderlyProducer;
import com.mlzj.rocketmq.demo.MyTransactionProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjApplicationTests {

    @Resource
    private MyConcurrentProducer myConcurrentProducer;

    @Resource
    private MyOrderlyProducer myOrderlyProducer;

    @Resource
    private MyTransactionProducer myTransactionProducer;
    @Test
    public void contextLoads() throws InterruptedException {
        String str = "yuehaoliang";
        myConcurrentProducer.pushMessage(str);

    }

    @Test
    public void orderlyProducer(){
        String str = "2019/3/27";
        myOrderlyProducer.pushMessage(str);
    }

    @Test
    public void transactionalProducer(){
        String str = "transactional message";
        String dbData = "1232131312";
        myTransactionProducer.pushMessage(str,dbData);
    }

}
