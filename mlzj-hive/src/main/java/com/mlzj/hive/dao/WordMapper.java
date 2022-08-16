package com.mlzj.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.hive.entity.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WordMapper extends BaseMapper<Word> {


    void loadPathOverwrite(@Param("path") String path, @Param("tableName") String tableName);

    void loadPathInto(@Param("path") String path, @Param("tableName") String tableName);

}
