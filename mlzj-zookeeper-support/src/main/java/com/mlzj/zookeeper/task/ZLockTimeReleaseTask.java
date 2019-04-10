package com.mlzj.zookeeper.task;

import com.mlzj.zookeeper.model.ZLockTimeRelease;
import com.mlzj.zookeeper.utils.ThreadPool;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 监控需要定时解锁的任务,单线程循环所有的锁
 * @author yhl
 * @date 2019/4/9
 */
@Component
@Slf4j
@Getter
public class ZLockTimeReleaseTask implements Runnable {

    @Resource
    private ThreadPool threadPool;
    /**
     * 任务集合
     */
    private Map<String, ZLockTimeRelease> taskMap = new ConcurrentHashMap<>(32);


    /**
     * 执行任务
     */
    @PostConstruct
    private void execute() {
        threadPool.getThreadPoolExecutor().execute(this);
    }

    @Override
    public void run() {
        log.info("开始执行解锁任务监听");
        while (true) {
            for (Map.Entry<String, ZLockTimeRelease> entry : taskMap.entrySet()) {
                ZLockTimeRelease zLockTimeRelease = entry.getValue();
                try {
                    if (System.currentTimeMillis() >= zLockTimeRelease.getExpiredTime()) {
                        Stat stat = zLockTimeRelease.getZLock().getZkClient().checkExists().forPath(zLockTimeRelease.getZLock().getPath());
                        if (Objects.nonNull(stat)) {
                            zLockTimeRelease.getZLock().getZkClient().delete().forPath(zLockTimeRelease.getZLock().getPath());
                            zLockTimeRelease.getZLock().setLocked(false);
                        }
                        taskMap.remove(entry.getKey());
                    }
                } catch (Exception e) {
                    log.error("解锁失败,节点信息{}", zLockTimeRelease.getZLock().getPath(), e);
                }

            }
        }
    }
}
