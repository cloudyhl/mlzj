package com.mlzj.commontest.listener;

import com.mlzj.commontest.event.TestEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 事务消息监听器
 * @author yhl
 * @date 2019/4/26
 */
@Component
public class TransactionListener{

    /**
     * fallbackExecution 当没有事务时是否执行该事件
     */
    @Async
    @TransactionalEventListener(fallbackExecution = false)
    public void listener(TestEvent event){
        System.out.println(event.getEventName());
        System.out.println(event.getEventString());
    }

}
