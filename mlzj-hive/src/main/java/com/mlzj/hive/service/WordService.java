package com.mlzj.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mlzj.hive.entity.Word;

/**
 * @author yhl
 * @date 2022/8/15
 */
public interface WordService extends IService<Word> {

    /**
     * 从路径读取数据到表 覆盖表所有数据
     * @param path 路径
     */
    void loadPathOverwrite (String path, String tableName);

    /**
     * 从路劲读取数据到表 用于追加数据
     * @param path 路径
     */
    void loadPathInto (String path, String tableName);


}
