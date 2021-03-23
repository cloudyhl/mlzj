package com.mlzj.commontest.demo.forkjoin;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 执行各种其余操作的任务
 * @author yhl
 * @date 2020/4/21
 */
public class ApiTask implements Callable<Object> {

    private List<Object> objects;
    //这里可以考虑吧需要调用api的service传进来方便run方法调用

    public ApiTask(List<Object> objects) {
        this.objects = objects;
    }

    @Override
    public Object call() throws Exception {
        objects.forEach(data->{
            //对api进行处理将数据处理结束

            //模拟处理后的数据
            List<Object> after = new ArrayList<>();
            TaskPoolExecutor.gerThreadPool().submit(new DataBaseTask(after));
        });
        return null;
    }
}
