package com.mlzj.common.demo.blockqueue;

import com.mlzj.common.task.AbstractTimerTask;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yhl
 * @date 2019/4/12
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class TimerTaskTestVo extends AbstractTimerTask {

    private Integer i;

    public TimerTaskTestVo(Integer i, long timeOut) {
        super(timeOut);
        this.i = i;
    }

    @Override
    public void execute() {
        log.info("执行结果"+i);
        int i = 1/0;
    }
}
