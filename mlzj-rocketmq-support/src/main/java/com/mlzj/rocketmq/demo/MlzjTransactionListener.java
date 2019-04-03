package com.mlzj.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author yhl
 * @date 2019/3/25
 */
@Slf4j
public class MlzjTransactionListener implements TransactionListener {
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            String transactionMessage = (String) o;
            //模拟本地事务
            log.info("local transaction save success  data:{}", transactionMessage);
            return LocalTransactionState.COMMIT_MESSAGE;
        }catch (Exception e){
            log.error("local transaction save failed exception message:",e);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    /**
     * 用于事务回查
     * @param messageExt 消息
     * @return 事务回查结果
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        log.info("接收到消息回查,data:{}",messageExt);
        log.info("完成事务查询,确认本地事务是否操作成功");
        log.info("返回确认结果,回滚或提交成功");
        if (new String(messageExt.getBody()).equals("Hello1")){
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        return LocalTransactionState.UNKNOW;
    }
}
