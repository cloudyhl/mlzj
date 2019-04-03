package com.mlzj.rocketmq.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author yhl
 * @date 2019/3/25
 */
@Slf4j
public class MlzjTransactionProducer {

    public static void main(String[] args) throws Exception{
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("WeiXin");
        transactionMQProducer.setNamesrvAddr("47.94.162.134:9876");
        transactionMQProducer.setTransactionListener(new MlzjTransactionListener());
        transactionMQProducer.start();
        try {

            for (int i = 0; i < 10;i++ ){
                Message message = new Message("WeiXintest","test",("Hello"+i).getBytes());
                String mysqlSave = "mysql" + i;
                TransactionSendResult transactionSendResult = transactionMQProducer.sendMessageInTransaction(message,mysqlSave);
                log.info("返回结果:{}",transactionSendResult);
            }
        } catch (Exception e) {
            log.error("send message fail");
        }
        transactionMQProducer.shutdown();
    }
}
