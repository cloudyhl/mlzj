package com.mlzj.hbase.config;

import java.io.IOException;
import java.util.function.Supplier;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * @author yhl
 * @date 2022/8/18
 */
@Configuration
public class HbaseConfig {

    @Value("${hbase.zookeeper.quorum}")
    private String quorum;

    @Bean
    public org.apache.hadoop.conf.Configuration getConfig(){
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set(HConstants.ZOOKEEPER_QUORUM,quorum);
        return conf;
    }
    //每次用户调用get方法获得一个新的数据库连接  可以考虑并发
    @Bean
    public Supplier<Connection> hbaseConnSupplier(){
        return this::hbaseConnect;
    }
    //获取数据库连接
    @Bean
    @Scope("prototype")
    public Connection hbaseConnect(){
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(this.getConfig());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
