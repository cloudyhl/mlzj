package com.mlzj.redis.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis watch测试
 * @author yhl
 * @date 2019/5/9
 */
@Component
public class RedisWatchTest {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void watch(){
        redisTemplate.watch("watchKey");
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        redisTemplate.multi();
        ops.set("watchKey", "abd");
        redisTemplate.exec();
    }
}
