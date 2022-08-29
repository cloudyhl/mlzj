package com.mlzj.hbase.service.impl;

import com.mlzj.hbase.dto.UserEntity;
import com.mlzj.hbase.service.HbaseService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

/**
 * @author yhl
 * @date 2022/8/18
 */
@Service
public class HbaseServiceImpl implements HbaseService {

    @Resource
    private Connection hbaseConnection;

    //插入数据
    @Override
    public void insert(UserEntity user) {
        try {
            //获取数据库中的表
            Table table = null;
            table = hbaseConnection.getTable(TableName.valueOf("hbase_test:user"));
            //准备一行数据
            Put line = new Put(UUID.randomUUID().toString().getBytes());
            line.addColumn("base".getBytes(), "USER_NAME".getBytes(), user.getUserName().getBytes());
            line.addColumn("base".getBytes(), "BIRTHDAY".getBytes(), user.getBirthday().getBytes());
            //将数据插入数据库
            table.put(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() throws IOException {
        Admin admin = hbaseConnection.getAdmin();
//        admin.createNamespace(NamespaceDescriptor.create("hbase").build());
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf("hbase:PHONE"));
        ColumnFamilyDescriptorBuilder columnDescBuilder = ColumnFamilyDescriptorBuilder
                .newBuilder(Bytes.toBytes("base")).setBlocksize(32 * 1024)
                .setCompressionType(Algorithm.NONE).setDataBlockEncoding(DataBlockEncoding.NONE);
        tableDescriptorBuilder.setColumnFamily(columnDescBuilder.build());
        TableDescriptor build = tableDescriptorBuilder.build();
        admin.createTable(build);
    }

    //根据rowkey获取数据
    @Override
    public UserEntity findByRowKey(String rowKey) {
        Table table = null;
        Result result;
        UserEntity userEntity = new UserEntity();
        try {
            table = hbaseConnection.getTable(TableName.valueOf("hbase_test:user"));
            Get get = new Get(rowKey.getBytes());
            result = table.get(get);
            userEntity.setUserName(new String(result.getValue("base".getBytes(), "USER_NAME".getBytes())));
            userEntity.setBirthday(new String(result.getValue("base".getBytes(), "BIRTHDAY".getBytes())));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userEntity;
    }

    @Override
    public List<UserEntity> findAll(){
        List<UserEntity> list = new ArrayList<>();
        Table table = null;
        try {
            table = hbaseConnection.getTable(TableName.valueOf("hbase_test:user"));
            Scan scan = new Scan();
            ResultScanner rs = table.getScanner(scan);
            Result result = null;
            while ((result =rs.next())!= null){
                UserEntity userEntity = new UserEntity();
                userEntity.setId(new String(result.getRow()));
                userEntity.setUserName(new String(result.getValue("base".getBytes(), "USER_NAME".getBytes())));
                userEntity.setBirthday(new String(result.getValue("base".getBytes(), "BIRTHDAY".getBytes())));
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
        return list;
    }


    @Override
    public List<UserEntity> findUserByPrefixName(String prefixName){
        Table table = null;
        List<UserEntity> list = new ArrayList<>();
        try {
            table = hbaseConnection.getTable(TableName.valueOf("hbase_test:user"));
            Scan scan = new Scan();
            SingleColumnValueFilter vf = new SingleColumnValueFilter(
                    "base".getBytes(),
                    "user_name".getBytes(),
                    CompareOperator.EQUAL,
                    new SubstringComparator(prefixName)
            );
            scan.setFilter(vf);
            ResultScanner scanner = table.getScanner(scan);
            Result result;
            while ((result=scanner.next()) != null){
                UserEntity userEntity = new UserEntity();
                userEntity.setId(new String(result.getRow()));
                userEntity.setUserName(new String(result.getValue("base".getBytes(), "USER_NAME".getBytes())));
                userEntity.setBirthday(new String(result.getValue("base".getBytes(), "BIRTHDAY".getBytes())));
                list.add(userEntity);            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
