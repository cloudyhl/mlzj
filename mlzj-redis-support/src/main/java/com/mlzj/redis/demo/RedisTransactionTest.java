package com.mlzj.redis.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/4/16
 */
@Service
public class RedisTransactionTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * redis支持数据库事务,必须先开启数据库事务,引入数据库，保证存在事务管理器
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveSomeData(){
        redisTemplate.multi();
    }
}
