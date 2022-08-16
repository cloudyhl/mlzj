package com.mlzj.hdfs.service;

import java.io.IOException;

/**
 * @author yhl
 * @date 2022/8/12
 */
public interface MapReduceService {

    void startJob(String path) throws InterruptedException, IOException, ClassNotFoundException;

}
