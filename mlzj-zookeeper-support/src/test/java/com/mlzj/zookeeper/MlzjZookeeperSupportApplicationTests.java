package com.mlzj.zookeeper;

import com.mlzj.zookeeper.demo.PathNodeChangeListener;
import com.mlzj.zookeeper.model.AbstractBaseNodeCacheListener;
import com.mlzj.zookeeper.model.ZBlockingLock;
import com.mlzj.zookeeper.model.ZBlockingLockTimerTask;
import com.mlzj.zookeeper.model.ZLock;
import com.mlzj.zookeeper.task.ZLockTimerTaskExecuter;
import com.mlzj.zookeeper.utils.ZookeeperUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjZookeeperSupportApplicationTests {
    @Resource
    private ZookeeperUtils zookeeperUtils;

    @Resource
    private ZLockTimerTaskExecuter zLockTimerTaskExecuter;

    @Test
    public void contextLoads() {
    }

    /**
     * 测试锁
     */
    @Test
    public void zookeeperlock() throws Exception {
        CuratorFramework client = zookeeperUtils.getClient();
        InterProcessMutex interProcessMutex = zookeeperUtils.getInterProcessMutex("/lock");
        interProcessMutex.acquire();
        System.out.println(1);
        Thread.sleep(1000000000);
    }
    /**
     * 测试锁 1
     */
    @Test
    public void zookeeperlock1() throws Exception {
        CuratorFramework client = zookeeperUtils.getClient();
        InterProcessMutex interProcessMutex = zookeeperUtils.getInterProcessMutex("/lock");
        interProcessMutex.acquire();
        interProcessMutex.release();
        System.out.println(1);
    }
    @Test
    public void zookeeperTest() throws Exception{
        DistributedBarrier distributedBarrier = zookeeperUtils.getDistributedBarrier("/barrier");
        Thread.sleep(1000);
        distributedBarrier.removeBarrier();

    }

    @Test
    public void barrier() throws Exception {
        DistributedBarrier distributedBarrier = zookeeperUtils.getDistributedBarrier("/barrier");
        distributedBarrier.setBarrier();
        distributedBarrier.waitOnBarrier();
        System.out.println("barrier1 start");
    }
    @Test
    public void barrier1() throws Exception {
        DistributedBarrier distributedBarrier = zookeeperUtils.getDistributedBarrier("/barrier");
        distributedBarrier.setBarrier();
        distributedBarrier.waitOnBarrier();
        System.out.println("barrier2 run");

    }
    @Test
    public void integer() throws Exception{
        DistributedAtomicInteger distributedAtomicInteger = zookeeperUtils.getDistributedAtomicInteger("/integer");
        AtomicValue<Integer> add = distributedAtomicInteger.add(1);
        System.out.println(add.preValue());
    }
    @Test
    public void integer1() throws Exception{
        DistributedAtomicInteger distributedAtomicInteger = zookeeperUtils.getDistributedAtomicInteger("/integer");
        System.out.println(distributedAtomicInteger.get().succeeded());
    }

    @Test
    public void getData() throws Exception{
        CuratorFramework client = zookeeperUtils.getClient();
        client.checkExists().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) throws Exception {
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted){
                    this.notifyAll();
                }
            }
        }).forPath("/lock");
        //client.create().inBackground(new BackGroundCallBackDemo()).forPath("/callback","data".getBytes());
        client.delete().inBackground(new BackGroundCallBackDemo()).forPath("/callback");
        Thread.sleep(10000);
    }

    class BackGroundCallBackDemo implements BackgroundCallback{

        @Override
        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
            System.out.println(curatorEvent.getType());
            System.out.println(new String(curatorEvent.getData()));
        }
    }

    @Test
    public void getThread(){
        System.out.println(Thread.currentThread());
    }

    @Test
    public void zkLock() throws InterruptedException {
        ZLock lock = zookeeperUtils.lock("/zkLock",20000L);
        if (lock.isLocked()){
            System.out.println("lock获取锁成功");
        }
        Thread.sleep(10000);
        zookeeperUtils.unLock(lock,"/zkLock");
    }

    @Test
    public void zkLock1(){
        ZLock lock = zookeeperUtils.lock("/zkLock");
        if (lock.isLocked()){
            System.out.println("lock1获取锁成功");
        }
        System.out.println("lock1获取锁失败");
    }
    @Test
    public void zkWatcher() throws Exception {
        zookeeperUtils.getNodeCache("/zkData",new NodeCacheListener());
        Thread.sleep(1000000);
    }

    @Test
    public void zkWatcherNodeChange() throws Exception {
        //zookeeperUtils.getClient().create().forPath("/zkData","hahahaha".getBytes());
        zookeeperUtils.getClient().delete().forPath("/zkData");
    }

    class NodeCacheListener extends AbstractBaseNodeCacheListener {


        @Override
        public void nodeChanged() throws Exception {
            System.out.println(new String(super.getNodeCache().getCurrentData().getData()));
            System.out.println(super.getNodeCache().getCurrentData().getPath());
        }
    }

    @Test
    public void pathChildChange() throws Exception {
        PathChildrenCache pathChildrenCache = zookeeperUtils.getPathChildrenCache("/father", new PathNodeChangeListener(), PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        Thread.sleep(2000);
        List<ChildData> currentData = pathChildrenCache.getCurrentData();
        System.out.println(currentData);
        Thread.sleep(10000000);
    }
    @Test
    public void pathChildChangeEventPush() throws Exception{
        CuratorFramework client = zookeeperUtils.getClient();
        client.create().forPath("/father/child2","yhl".getBytes());
    }

    @Test
    public void testWaitLock() throws Exception {
        CuratorFramework client = zookeeperUtils.getClient();
        client.checkExists().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) throws Exception {
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted){
                    this.notifyAll();
                }
            }
        }).forPath("/lock");
    }

    private synchronized void lock(CuratorFramework client, String path) throws Exception {
        while (true){
            Stat stat = client.checkExists().usingWatcher(new CuratorWatcher() {
                @Override
                public void process(WatchedEvent watchedEvent) throws Exception {
                    if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                        thisnotifyall();
                    }
                }
            }).forPath("/lock");
            if (stat != null){
                this.wait();
            }else {
                client.create().forPath(path);
                break;
            }
        }
    }

    private synchronized void thisnotifyall(){
        this.notifyAll();
    }

    @Test
    public void testLockWait() throws Exception {

        ZBlockingLock blockingLock = zookeeperUtils.getBlockingLock("/lock");

        blockingLock.lock();
        if (blockingLock.isLocked()){
            System.out.println("this has lock");
        }
        Thread.sleep(100000);
    }
    @Test
    public void testLockWait1() throws Exception{
        ZBlockingLock blockingLock = zookeeperUtils.getBlockingLock("/lock");
        blockingLock.lock();
        if (blockingLock.isLocked()){
            System.out.println("this has lock");
        }
        System.out.println("this has lock");
    }
    @Test
    public void deleteNode() throws Exception {
        zookeeperUtils.getClient().delete().forPath("/lock");
    }
}
