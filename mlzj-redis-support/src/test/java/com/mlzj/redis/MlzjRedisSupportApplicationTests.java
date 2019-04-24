package com.mlzj.redis;

import com.mlzj.redis.demo.RedisTransactionTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjRedisSupportApplicationTests {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Resource
    private RedisTransactionTest redisTransactionTest;
    @Resource
    RedissonClient redissonClient;


    @Test
    public void contextLoads() {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();

        //valueOperations.set("yhl",user);
        long l = System.currentTimeMillis();
        for (int i =0 ; i<1000000; i++){
            valueOperations.set("yhl"+i,"user");
            if (i % 10000 == 0){
                System.out.println("当前:"+i);
                System.out.println(+System.currentTimeMillis() - l);
                System.out.println("------------------");
            }
        }
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(valueOperations.get("yhl"));
    }

    @Test
    public void lock() throws InterruptedException {

        RLock abc = redissonClient.getLock("abc");
        abc.lock(1000,TimeUnit.MILLISECONDS);
        for (int i = 0; i < 100 ;i++ ){
            if (abc.isLocked()){
                Thread.sleep(3000);
            }
        }
        System.out.println(abc);
    }

    @Test
    public void lock2(){
        RLock abc = redissonClient.getLock("abc");
        System.out.println("获取锁");
        while (abc.isLocked()){
            System.out.println("abc is locked");
        }
        System.out.println("unlock");
    }
    @Test
    public void unlock(){
        RLock abc = redissonClient.getLock("abc");
        abc.unlock();
    }

    @Test
    public void redisTransaction(){
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("abd","123");
                int i = 1/0;
                operations.opsForValue().set("abr","123");
                operations.opsForValue().set("abt","123");
                List exec = operations.exec();
                return exec;
            }
        });
    }

    @Test
    public void redisTest(){
        redisTransactionTest.saveSomeData();
    }

    @Test
    public void handleSet(){
        BoundSetOperations<String, String> student = redisTemplate.boundSetOps("student");
        System.out.println(student.members());
    }
}
