package com.mlzj.hbase;

import com.mlzj.hbase.dto.UserEntity;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.phoenix.jdbc.PhoenixDriver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MlzjHbaseApplicationTests {

    @Resource
    private Connection hbaseConnection;

    @Test
    void dropTable() throws IOException {
        Admin admin = hbaseConnection.getAdmin();
        admin.disableTable(TableName.valueOf("mydemo:userinfos"));
        admin.deleteTable(TableName.valueOf("mydemo:userinfos"));

    }

    @Test
    void fun() throws SQLException {
        java.sql.Connection connection = null;
        try {
            Class.forName(PhoenixDriver.class.getName());
            connection = DriverManager.getConnection("jdbc:phoenix:localhost:2181");

            String sql = "select * from PHONE";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("结果为");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("mycolumn"));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("加载Phoenix驱动失败!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取Phoenix JDBC连接失败!");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Test
    public void insertToPhone() throws IOException {

        Table table = null;
        table = hbaseConnection.getTable(TableName.valueOf("PHONE"));
        Put line = new Put(UUID.randomUUID().toString().getBytes());
        line.addColumn("base".getBytes(), "PHONE".getBytes(), "123".getBytes());
        line.addColumn("base".getBytes(), "YYS".getBytes(),"2022-09-08".getBytes());
        line.addColumn("base".getBytes(), "ADDRESS".getBytes(),"这个就是地址".getBytes());
        //将数据插入数据库
        table.put(line);
    }

    @Test
    public void findAll(){
        List<UserEntity> list = new ArrayList<>();
        Table table = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf("PHONE"));
            Scan scan = new Scan();
            ResultScanner rs = table.getScanner(scan);
            Result result = null;
            while ((result =rs.next())!= null){
                UserEntity userEntity = new UserEntity();
                userEntity.setId(new String(result.getRow()));
                userEntity.setUserName(new String(result.getValue("base".getBytes(), "PHONE".getBytes())));
                userEntity.setBirthday(new String(result.getValue("base".getBytes(), "YYS".getBytes())));
                list.add(userEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void enableTable() throws IOException {
        Admin admin = hbaseConnection.getAdmin();
        admin.enableTable(TableName.valueOf("hbase_test:USER"));
        System.out.println(1);
    }
}
