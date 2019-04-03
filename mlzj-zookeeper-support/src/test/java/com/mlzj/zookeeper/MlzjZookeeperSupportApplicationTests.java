package com.mlzj.zookeeper;

import com.mlzj.zookeeper.config.ZookeeperUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjZookeeperSupportApplicationTests {
    @Resource
    private ZookeeperUtils zookeeperUtils;

    @Test
    public void contextLoads() {
    }

    @Test
    public void zookeeperTest() throws Exception {
        CuratorFramework client = zookeeperUtils.getClient();
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/root","yhl".getBytes());
    }
}
