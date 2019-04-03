package com.mlzj.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjRedisSupportApplicationTests {

    @Resource
    RedisTemplate<String, String> redisTemplate;

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
        abc.lock();
        for (int i = 0; i < 10 ;i++ ){
            if (abc.isLocked()){
                Thread.sleep(3000);
            }
        }
        abc.unlock();
        System.out.println(abc);
    }

    @Test
    public void lock2(){
        RLock abc = redissonClient.getLock("abc");
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
}
