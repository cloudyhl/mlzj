package com.mlzj.hbase.service;

import com.mlzj.hbase.dto.UserEntity;
import java.io.IOException;
import java.util.List;

/**
 * @author yhl
 * @date 2022/8/18
 */
public interface HbaseService {

    /**
     * 插入user数据
     * @param user 用户数据
     */
    void insert(UserEntity user);

    /**
     * 创建表和命名空间
     * @throws IOException IO异常
     */
    void createTable() throws IOException;

    /**
     * 根据rowKey查询数据
     * @param rowKey rowKey
     * @return 查询结果
     */
    UserEntity findByRowKey(String rowKey);

    /**
     * 查询所有
     * @return 查询结果
     */
    List<UserEntity> findAll();

    /**
     * 通过名称查询
     * @param prefixName 名称
     * @return 查询结果
     */
    List<UserEntity> findUserByPrefixName(String prefixName);
}
