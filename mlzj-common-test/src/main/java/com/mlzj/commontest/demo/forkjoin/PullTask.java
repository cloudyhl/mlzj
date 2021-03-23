package com.mlzj.commontest.demo.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author yhl
 * @date 2020/4/21
 */
public class PullTask extends RecursiveTask<Integer> {

    /**
     * 模拟传入所有分段的类目
     */
    private List<Integer> codes;

    /**
     * 最大任务数
     */
    private Integer maxListSize = 1000;
    /**
     * 拉取任务缓冲区
     */
    private Integer maxDataSize = 3000;

    private List<Object> objectsBuffer = new ArrayList<>(3000);


    public PullTask(List<Integer> codes) {
        this.codes = codes;
    }

    @Override
    protected Integer compute() {
        if (codes.size() > maxListSize) {
            Integer middleSize = codes.size() / 2;
            List<Integer> codesLeft = new ArrayList<>(codes.subList(0, middleSize));
            List<Integer> codesRight = new ArrayList<>(codes.subList(middleSize, codes.size()));
            PullTask pullTaskLeft = new PullTask(codesLeft);
            PullTask pullTaskRight = new PullTask(codesRight);
            invokeAll(pullTaskLeft, pullTaskRight);
            return pullTaskLeft.join() + pullTaskRight.join();
        } else {
            //具体业务处理放在这里

            //此处模拟调用唯品会api返回的商品
            codes.forEach(data->{
                List<Object> objects = new ArrayList<>();
                objectsBuffer.addAll(objects);
                if (objectsBuffer.size() > maxDataSize){
                    TaskPoolExecutor.gerThreadPool().submit(new ApiTask(new ArrayList<>(objectsBuffer)));
                    objectsBuffer.clear();
                }
            });

            return codes.stream().mapToInt(data-> data).sum();
        }
    }
}
