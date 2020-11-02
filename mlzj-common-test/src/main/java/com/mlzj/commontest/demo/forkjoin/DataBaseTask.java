package com.mlzj.commontest.demo.forkjoin;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 执行数据库任务
 * @author yhl
 * @date 2020/4/21
 */
public class DataBaseTask implements Callable<Object> {

    private List<Object> objects;
    //这里传入service方便批量保存

    public DataBaseTask(List<Object> objects) {
        this.objects = objects;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
