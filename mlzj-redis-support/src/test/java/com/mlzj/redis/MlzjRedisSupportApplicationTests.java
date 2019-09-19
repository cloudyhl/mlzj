package com.mlzj.redis;

import com.mlzj.redis.demo.RedisTransactionTest;
import com.mlzj.redis.demo.RedisWatchTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjRedisSupportApplicationTests {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Resource
    private RedisTransactionTest redisTransactionTest;
    @Resource
    RedissonClient redissonClient;

    @Resource
    private RedisWatchTest redisWatchTest;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void contextLoads() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        //valueOperations.set("yhl",user);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            valueOperations.set("yhl" + i, "user");
            if (i % 10000 == 0) {
                System.out.println("当前:" + i);
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
        abc.lock(1000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 100; i++) {
            if (abc.isLocked()) {
                Thread.sleep(3000);
            }
        }
        System.out.println(abc);
    }

    @Test
    public void lock2() {
        RLock abc = redissonClient.getLock("abc");
        System.out.println("获取锁");
        abc.lock();
        abc.unlock();
        while (abc.isLocked()) {
            System.out.println("abc is locked");
        }
        System.out.println("unlock");
    }

    @Test
    public void unlock() {
        RLock abc = redissonClient.getLock("adc");
        abc.unlock();
    }

    @Test
    public void redisTransaction() {
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("abd", "123");
                int i = 1 / 0;
                operations.opsForValue().set("abr", "123");
                operations.opsForValue().set("abt", "123");
                List exec = operations.exec();
                return exec;
            }
        });
    }

    @Test
    public void redisTest() {
        redisTransactionTest.saveSomeData();
    }

    @Test
    public void handleSet() {
        BoundSetOperations<String, String> student = redisTemplate.boundSetOps("student");
        System.out.println(student.members());
    }

    @Test
    public void redissonBalockTest() throws InterruptedException {
        RLock xxxx = redissonClient.getLock("yuehaoliang");
        boolean b = xxxx.tryLock(0, 60, TimeUnit.SECONDS);
        System.out.println(b);
        System.out.println(System.currentTimeMillis());
        System.out.println("xxxxxxxx");
        System.out.println("go out");
        System.out.println(xxxx.isLocked());
        xxxx.unlock();
    }

    @Test
    public void redissonBalockTest1() throws InterruptedException {
        RLock xxxx = redissonClient.getLock("yuehaoliang");
        boolean b = xxxx.tryLock(0, 60, TimeUnit.SECONDS);
        System.out.println(b);
        System.out.println(System.currentTimeMillis());
        System.out.println(xxxx.isLocked());
        xxxx.unlock();
    }

    //redisTemplate 事务需要使用 SessionCallBack
    @Test
    public void redisWatch() {
        //redisTemplate.watch("hash");
        redisTemplate.execute(new SessionCallback<Object>() {

            @Override
            public Object execute(@NonNull RedisOperations operations) throws DataAccessException {

//                operations.watch("hash");
//                operations.watch("count");
//                BoundHashOperations<String, String, Integer> hash = operations.boundHashOps("hash");
//                Integer count = hash.get("count");
//                operations.multi();
//                count--;
//                hash.put("count", count);
                operations.watch("price");
                Integer price = (Integer) operations.opsForValue().get("price");
                operations.multi();
                price++;
                operations.opsForValue().set("price", price);
                List exec = operations.exec();
                System.out.println(exec);
                operations.unwatch();
                return null;
            }
        });
        redisTemplate.unwatch();
    }

    @Test
    public void redisWatch2() {
        //redisTemplate.watch("hash");
        redisTemplate.execute(new SessionCallback<Object>() {

            @Override
            public Object execute(@NonNull RedisOperations operations) throws DataAccessException {
//                operations.watch("hash");
//                operations.watch("count");
//                BoundHashOperations<String, String, Integer> hash = operations.boundHashOps("hash");
//                Integer count = hash.get("count");
//                operations.multi();
//                count++;
//                hash.put("count", count);
                operations.watch("price");
                Integer price = (Integer) operations.opsForValue().get("price");
                operations.multi();
                price--;
                operations.opsForValue().set("price", price);
                List exec = operations.exec();
                System.out.println(exec);
                operations.unwatch();
                return null;
            }
        });
        //redisTemplate.unwatch();
    }

    @Test
    public void cas() throws InterruptedException, ExecutionException {
        String key = "test-cas-1";
        ValueOperations<String, String> strOps = redisTemplate.opsForValue();
        strOps.set(key, "hello");
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final int idx = i;
            tasks.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    return redisTemplate.execute(new SessionCallback() {
                        @Override
                        public Object execute(RedisOperations operations) throws DataAccessException {
                            operations.watch(key);
                            String origin = (String) operations.opsForValue().get(key);
                            operations.multi();
                            operations.opsForValue().set(key, origin + idx);
                            Object rs = operations.exec();
                            System.out.println("set:" + origin + idx + " rs:" + rs);
                            return rs;
                        }
                    });
                }
            });
        }
        List<Future<Object>> futures = pool.invokeAll(tasks);
        for (Future<Object> f : futures) {
            System.out.println(f.get());
        }
        pool.shutdown();
        pool.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void watchTransaction() {
        redisTemplate.watch("watchKey");
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set
        redisTemplate.multi();
        ops.set("watchKey", "abd");
        redisTemplate.exec();
    }

    @Test
    public void watchTransaction1() {
        redisTemplate.watch("watchKey");
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        redisTemplate.multi();
        ops.set("watchKey", "abdec");
        redisTemplate.exec();
    }

    @Test
    public void setRedisWatchTest() {
        redisWatchTest.watch();
    }

    //121090
    @Test
    public void testRedisWriteSpeed() {
        long l = System.currentTimeMillis();
//        for (int i =0; i<200000; i++){
//            stringRedisTemplate.opsForValue().set("test"+i,"test"+i);
//            if (i % 50000 == 0){
//                System.out.println(i);
//            }
//        }
//        for (int j = 0; j < 4; j++) {
//            final int x = j;
//            redisTemplate.execute((RedisCallback<Object>) connection -> {
//                connection.openPipeline();
//                int num = x * 50000;
//                int end = num + 50000;
//                for (int i = num; i < end; i++) {
//                    stringRedisTemplate.opsForValue().set("test" + i, "test" + i);
//                    if (i % 50000 == 0) {
//                        System.out.println(i);
//                    }
//                }
//                connection.closePipeline();
//                return null;
//            });
//        }

        stringRedisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.openPipeline();
            for (int i = 0; i < 200000; i++) {
                stringRedisTemplate.opsForValue().set("test" + i, "test" + i);
                if (i % 50000 == 0) {
                    System.out.println(i);
                }
            }
            connection.closePipeline();
            return null;
        });
        System.out.println(System.currentTimeMillis() - l);
    }
    //redisson提供阻塞队列
    @Test
    public void rBlockQueuePut(){
        RBlockingDeque<String> rBlockQueue = redissonClient.getBlockingDeque("RBlockQueue");
        rBlockQueue.offer("abc");
        rBlockQueue.offer("adef");
        rBlockQueue.offer("ahig");
    }

    @Test
    public void rBlockQueueTake() throws InterruptedException {
        RBlockingDeque<String> rBlockQueue = redissonClient.getBlockingDeque("RBlockQueue");
        while (true){
            String take = rBlockQueue.poll(3,TimeUnit.SECONDS);
            String s = rBlockQueue.pollFirstFromAny(3, TimeUnit.SECONDS, "abc", "adef");
            System.out.println(s);
            System.out.println("get message");
            System.out.println(take);

        }
    }

    //RBoundedBlockingQueue 可以设置具体阻塞队列的大小
    @Test
    public void rBoundedBlockingQueueOffer() throws InterruptedException {
        RBoundedBlockingQueue<Object> rBoundedBlockingQueue = redissonClient.getBoundedBlockingQueue("rBoundedBlockingQueue");
        rBoundedBlockingQueue.trySetCapacity(2);
        rBoundedBlockingQueue.offer("xxa");
        rBoundedBlockingQueue.offer("xxb");
        //rBoundedBlockingQueue.put("xxe");
        rBoundedBlockingQueue.offer("xxc");
    }
    @Test
    public void rBoundedBlockingQueueTake() throws InterruptedException {
        RBoundedBlockingQueue<String> rBoundedBlockingQueue = redissonClient.getBoundedBlockingQueue("rBoundedBlockingQueue");
        rBoundedBlockingQueue.trySetCapacity(2);
        while (true){
            System.out.println("Start get message");
            String take = rBoundedBlockingQueue.take();
            System.out.println(take);
        }
    }

    class RedisScript extends DefaultRedisScript<String>{

    }

    @Test
    public void redisLua(){
        List<String> lockKey = new ArrayList<>();
        String key = "lock";
        lockKey.add(key);
        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(String.class);
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luaScript/GetLock.lua")));
        stringRedisTemplate.execute(defaultRedisScript,new StringRedisSerializer(),new StringRedisSerializer(),lockKey,"xxx","10");
    }
    @Test
    public void redisLuaUseSimple(){
        List<String> lockKey = new ArrayList<>();
        String key = "lock";
        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(String.class);
        defaultRedisScript.setScriptText("if redis.call('setNx',KEYS[1],ARGV[1]) then\n" +
                "    if redis.call('get',KEYS[1])==ARGV[1] then\n" +
                "        return redis.call('expire',KEYS[1],ARGV[2])\n" +
                "    else\n" +
                "        return 0\n" +
                "    end\n" +
                "end");
        stringRedisTemplate.execute(defaultRedisScript,new StringRedisSerializer(),new StringRedisSerializer(),lockKey,"xxx","10");
    }
}
