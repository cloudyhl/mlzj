package com.mlzj.spark.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

/**
 * @author yhl
 * @date 2022/8/15
 */
@Slf4j
public class CustomReceiver extends Receiver<String> {

    public CustomReceiver(StorageLevel storageLevel) {
        super(storageLevel);
    }


    @Override
    public void onStart() {
        System.out.println("is start");
        new Thread(this::doStart).start();
    }

    @Override
    public void onStop() {
        System.out.println("is stop");
    }

    private void doStart() {
        while (!isStopped()) {
            int value = RandomUtils.nextInt(100);
            if (value < 20) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    log.error("sleep exception", e);
                    restart("sleep exception", e);
                }

            }
            store(String.valueOf(value));
            this.stop("-------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>HAHAHAHAHA<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<------------------------------");
        }
    }
}
